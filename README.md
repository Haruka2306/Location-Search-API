# Location-Search-API

## API概要
店舗で、探している商品が含まれるコーナー名を検索すると商品が置いてあるロケーション名とおおよその位置情報を知ることができます。

## 作成背景
勤務していた店舗では、毎週商品の位置が変更しており、お客様から商品の場所を聞かれることがかなり多かった。
また、従業員も毎週商品の位置を把握するのが大変だった。
そこで、商品の位置情報検索機能を持つ簡単なアプリを自作しようと思った。

## API仕様書
[SwaggerによるAPI仕様書はこちら](https://haruka2306.github.io/Location-Search-API/)

## API概略図
![Location-Search-API drawio (6)](https://github.com/Haruka2306/Location-Search-API/assets/137120436/07d337e7-3d9e-4d03-811c-54009ce088c4)

## 使用技術
* Java 17
* Spring Boot 3.1.3
* MySQL 8.0.34
* Docker 24.0.5
  
## DB登録済みデータ
| id | corner | location_name | place | creator| date_created|
| ---- | ---- | ---- | ---- |----|----|
| 1 | food | A | left-back | yamada | 2023/08/01 |
| 2 | drink | B | center-back | yamada | 2023/08/01 |
| 3 | daily-necessities | C | right-back | yamada | 2023/08/01 |
| 4 | electronic-appliances | D | left-front | yamada | 2023/08/01 |
| 5 | outdoor-product | E | center-front | yamada | 2023/08/01 |
| 6 | toy | F | right-front | yamada | 2023/08/01 |


## 動作確認
<details>
<summary>1-1. POST実行</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations'

![location_post](https://github.com/Haruka2306/Location-Search-API/assets/137120436/5b57c8d1-b8c8-45f7-b2b3-db9dcf2c2fdb)

 
</div>
</details>

<details>
<summary>1-2. POST実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/game'

![location_post-check](https://github.com/Haruka2306/Location-Search-API/assets/137120436/df86df2c-b519-4a1b-8971-03e3687128fa)

</div>
</details>

<details>
<summary>1-3. POSTの例外・エラー処理</summary>
<div>
1. 入力項目が空文字の時
  
 ####
     curl --location 'http://localhost:8080/locations'

![location-post-null400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/ac866a00-8ec5-4091-afca-f48cceb3364e)
 
</div>

<div>
2. 入力項目が20文字以上の時
  
 ####
     curl --location 'http://localhost:8080/locations'

![location-post-20 charcters over 400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/a4e5004f-d7fa-4851-baa8-b4072f58e158)

</div>

<div>
3. location_nameが英字大文字一字で入力されていない場合
  
 ####
     curl --location 'http://localhost:8080/locations'

![location_post-location_name400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/ece80e9f-111a-489c-9a91-b6aee1fcaede)

</div>

<div>
4. created_dateが適切な形式で入力されていない場合
  
 ####
     curl --location 'http://localhost:8080/locations'

![location_post-created_date400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/6c375e9d-a2fa-44aa-9fd7-ca765fb691dd)

</div>

<div>
5. 既に登録済みのcorner名が入力された場合
  
 ####
     curl --location 'http://localhost:8080/locations'

![location_post409](https://github.com/Haruka2306/Location-Search-API/assets/137120436/b0f42f69-9c19-4398-b75d-b1235678c06b)

</div>
</details>

<details>
<summary>2-1. PATCH実行</summary>
<div>
  
 ####
     curl --location --request PATCH 'http://localhost:8080/locations/toy'

![location_patch](https://github.com/Haruka2306/Location-Search-API/assets/137120436/1fd0ae5f-e9b1-4c3c-ba18-295d68e409a2)

</div>
</details>

<details>
<summary>2-2. PATCH実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/toy'

![location_patch-check](https://github.com/Haruka2306/Location-Search-API/assets/137120436/b4172b62-f0a1-46a5-b7ec-5974e73f3fb7)

</div>
</details>

<details>
<summary>2-3. PATCHの例外・エラー処理</summary>
<div>
1. 入力項目が空文字の時
  
 ####
     curl --location 'http://localhost:8080/locations/toy'

![patch-null400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/d13a0676-2520-4ae5-ad25-52bc69caa00b)

 
</div>

<div>
2. 入力項目が20文字以上の時
  
 ####
     curl --location 'http://localhost:8080/locations/toy'

![patch-20 characters over 400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/3d5b0240-3c65-4197-88db-4cb4ca6ff443)


</div>

<div>
3. location_nameが英字大文字一字で入力されていない場合
  
 ####
     curl --location 'http://localhost:8080/locations/toy'

![patch-location_name400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/c4a3cd04-0b7f-4a47-af5c-dbf07f03651b)

</div>

<div>
4. created_dateが適切な形式で入力されていない場合
  
 ####
     curl --location 'http://localhost:8080/locations/toy'

![patch-created_date400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/979feff6-aca7-4e75-9b1f-fd1cdf28480b)


</div>

<div>
5. 指定したcornerがDBに登録されていない場合
  
 ####
     curl --location 'http://localhost:8080/locations/music'

![patch-404](https://github.com/Haruka2306/Location-Search-API/assets/137120436/9b54c4c8-eb85-4265-b773-dbd33068141f)

</div>
</details>

<details>
<summary>3-1. DELETE実行</summary>
<div>
  
 ####
     curl --location --request DELETE 'http://localhost:8080/locations/outdoor-product'

![location_delete](https://github.com/Haruka2306/Location-Search-API/assets/137120436/bc1967ac-510f-408c-b053-1a5c88b98415)

</div>
</details>

<details>
<summary>3-2. DELETE実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/outdoor-product'

![location_delete-check](https://github.com/Haruka2306/Location-Search-API/assets/137120436/c224621a-6e19-4aa1-acd3-dea6c4ca1740)

</div>
</details>

<details>
<summary>3-3. DELETEの例外・エラー処理</summary>
<div>
指定したcornerがDBに登録されていない場合
  
 ####
     curl --location 'http://localhost:8080/locations'

![delete-404](https://github.com/Haruka2306/Location-Search-API/assets/137120436/754d5932-0d04-4005-9737-b58c0adc1888)

</div>
</details>

### 今後の展望
* 自動テスト
* Spring Security

### 課題
お客様向け（取得機能のみ）、従業員向け（取得、登録、更新、削除機能）で機能を分ける必要がある。
