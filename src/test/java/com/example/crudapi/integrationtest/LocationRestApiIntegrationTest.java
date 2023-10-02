package com.example.crudapi.integrationtest;

import com.example.crudapi.controller.form.LocationForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LocationRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Nested
    class FindByCorner {
        @Test
        @DataSet(value = "datasets/locations.yml")
        @Transactional
        void 指定したcornerのlocationが取得できステータスコード200を返すこと() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.get("/locations/food"))
                            .andExpect(status().isOk())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                       "corner": "food",
                       "location_name": "A",
                       "place": "left-back",
                       "created_by": "yamada",
                       "created_date": "2023/08/01"
                     }
                        """, response, JSONCompareMode.STRICT
            );
        }

        @Test
        @DataSet(value = "datasets/locations.yml")
        @Transactional
        void 指定したcornerが存在しないときにステータスコード404とエラーメッセージを返すこと() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.get("/locations/music"))
                            .andExpect(status().isNotFound())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals(""" 
                    {
                       "status": "404",
                       "error": "Not Found",
                       "message": "No record found for corner",
                       "path": "/locations/music"
                    }
                                    
                    """, response, JSONCompareMode.STRICT
            );
        }
    }

    @Nested
    class CreateLocationTest {

        private static final ObjectMapper object_mapper = new ObjectMapper();

        @Test
        @DataSet(value = "datasets/locations.yml")
        @ExpectedDataSet(value = "datasets/insert_location.yml", ignoreCols = "corner")
        @Transactional
        void cornerを新規登録できること() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                             {
                                               "corner": "game",
                                               "location_name": "G",
                                               "place": "right-front",
                                               "created_by": "tanaka",
                                               "created_date": "2023/09/01"
                                             }
                                            """))
                            .andExpect(MockMvcResultMatchers.status().isCreated())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                       "message": "location successfully created"
                    }
                    """, response, JSONCompareMode.STRICT);
        }


        @Test
        @Transactional
        void 新規登録時に空文字で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("", "", "", "", "");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsString(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "corner": "Please enter",
                          "location_name": "Please enter in one capital letter of the alphabet",
                          "place": "Please enter",
                          "created_by": "Please enter",
                          "created_date": "Please enter in yyyy/mm/dd"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 新規登録時にcornerが20文字以上で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("aaaaaaaaaaaaaaaaaaaaa", "G", "right-front", "tanaka", "2023/09/01");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsBytes(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "corner": "Please enter up to 20 characters"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 新規登録時にplaceが20文字以上で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("game", "G", "aaaaaaaaaaaaaaaaaaaaa", "tanaka", "2023/09/01");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsBytes(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "place": "Please enter up to 20 characters"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 新規登録時にcreated_byが20文字以上で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("game", "G", "right-front", "aaaaaaaaaaaaaaaaaaaaa", "2023/09/01");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsBytes(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "created_by": "Please enter up to 20 characters"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 新規登録時にlocation_nameが英字大文字1字以外で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("game", "あ", "right-front", "tanaka", "2023/09/01");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsString(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "location_name": "Please enter in one capital letter of the alphabet"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 新規登録時にcreated_dateが指定した形式で入力されていない場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("game", "G", "right-front", "tanaka", "2023/09/012");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsString(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "created_date": "Please enter in yyyy/mm/dd"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @DataSet(value = "datasets/locations.yml")
        @Transactional
        void 既に登録済みのcornerが新規登録で渡された場合にステータスコード409とエラーメッセージを返すこと() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                              {
                                                "corner": "toy",
                                                "location_name": "G",
                                                "place": "right-front",
                                                "created_by": "tanaka",
                                                "created_date": "2023/09/01"
                                              }
                                            """))
                            .andExpect(status().isConflict())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals(""" 
                    {
                      "status": "409",
                       "error": "Conflict",
                       "message": "toy is already created",
                       "path": "/locations"
                    }
                    """, response, JSONCompareMode.STRICT);
        }
    }

    @Nested
    class UpdateLocationTest {

        private ObjectMapper object_mapper = new ObjectMapper();

        @Test
        @DataSet(value = "datasets/locations.yml")
        @ExpectedDataSet(value = "datasets/update_location.yml")
        @Transactional
        void 登録済みのcornerのlocationを更新できること() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/toy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                              {
                                                "corner": "toy",
                                                "location_name": "H",
                                                "place": "2F-right-front",
                                                "created_by": "suzuki",
                                                "created_date": "2023/09/08"
                                              }
                                            """))
                            .andExpect(status().isOk())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "message": "location successfully updated"
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時に空文字で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("", "", "", "", "");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/toy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsString(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "corner": "Please enter",
                          "location_name": "Please enter in one capital letter of the alphabet",
                          "place": "Please enter",
                          "created_by": "Please enter",
                          "created_date": "Please enter in yyyy/mm/dd"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時にcornerが20文字以上で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("aaaaaaaaaaaaaaaaaaaaa", "H", "2F-right-front", "suzuki", "2023/09/08");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/toy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsBytes(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "corner": "Please enter up to 20 characters"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時にplaceが20文字以上で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("toy", "H", "aaaaaaaaaaaaaaaaaaaaa", "suzuki", "2023/09/08");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/toy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsBytes(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "place": "Please enter up to 20 characters"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時にcreated_byが20文字以上で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("toy", "H", "2F-right-front", "aaaaaaaaaaaaaaaaaaaaa", "2023/09/08");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/toy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsBytes(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "created_by": "Please enter up to 20 characters"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時にlocation_nameが英字大文字1字以外で入力された場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("toy", "あ", "2F-right-front", "suzuki", "2023/09/08");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/toy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsString(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "location_name": "Please enter in one capital letter of the alphabet"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時にcreated_dateが指定した形式で入力されていない場合にステータスコード400とエラーメッセージが返されること() throws Exception {
            LocationForm form = new LocationForm("toy", "H", "2F-right-front", "suzuki", "20230908");
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/toy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(object_mapper.writeValueAsString(form)))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "status": "400",
                      "error": "Bad Request",
                      "message": {
                          "created_date": "Please enter in yyyy/mm/dd"
                      }
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @DataSet(value = "datasets/locations.yml")
        @Transactional
        void 更新時に指定したcornerが存在しないときにステータスコード404とエラーメッセージを返すこと() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/locations/music")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                              {
                                                "corner": "music",
                                                "location_name": "H",
                                                "place": "2F-right-front",
                                                "created_by": "suzuki",
                                                "created_date": "2023/09/08"
                                              }
                                            """))

                            .andExpect(status().isNotFound())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals(""" 
                    {
                       "status": "404",
                       "error": "Not Found",
                       "message": "No record found for corner",
                       "path": "/locations/music"
                    }
                                    
                    """, response, JSONCompareMode.STRICT);
        }
    }

    @Nested
    class DeleteLocationTest {
        @Test
        @DataSet(value = "datasets/locations.yml")
        @ExpectedDataSet(value = "datasets/delete_location.yml")
        @Transactional
        void 指定したcornerが削除できること() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.delete("/locations/outdoor-product"))
                            .andExpect(status().isOk())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "message": "location successfully deleted"
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @DataSet(value = "datasets/locations.yml")
        @Transactional
        void 削除時に指定したcornerが存在しないときにステータスコード404とエラーメッセージを返すこと() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.delete("/locations/music"))
                            .andExpect(status().isNotFound())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals(""" 
                    {
                       "status": "404",
                       "error": "Not Found",
                       "message": "No record found for corner",
                       "path": "/locations/music"
                    }
                                    
                    """, response, JSONCompareMode.STRICT
            );
        }
    }
}
