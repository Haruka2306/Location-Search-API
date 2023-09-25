# Location-Search-API

## API概要
店舗で、探している商品が含まれるコーナー名を検索すると商品が置いてあるロケーション名とおおよその位置情報を知ることができます。

## 作成背景
勤務していた店舗では、毎週商品の位置が変更しており、お客様から商品の場所を聞かれることがかなり多かった。
また、従業員も毎週商品の位置を把握するのが大変だった。
そこで、商品の位置情報検索機能を持つ簡単なアプリを自作しようと思った。

## API仕様書
[SwaggerによるAPI仕様書はこちら](http://localhost:63342/crudapi/docs/index.html?_ijt=45esk4ikore4em2uhnh652pg2k&_ij_reload=RELOAD_ON_SAVE)

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
<summary>POST実行</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations'

 ![location_post](https://github.com/Haruka2306/Location-Search-API/assets/137120436/53c037ec-4795-46b5-beac-3b712a3fe93b)

 
</div>
</details>

<details>
<summary>POST実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/game'

![location_post-check](https://github.com/Haruka2306/Location-Search-API/assets/137120436/5097835d-ff0a-4efb-a7e2-d56bbe043ebc)

</div>
</details>

<details>
<summary>PATCH実行</summary>
<div>
  
 ####
     curl --location --request PATCH 'http://localhost:8080/locations/toy'

![location_patch](https://github.com/Haruka2306/Location-Search-API/assets/137120436/31c305eb-0ee4-4f03-8fe8-9af92df604c4)

</div>
</details>

<details>
<summary>PATCH実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/toy'

![location_patch-check](https://github.com/Haruka2306/Location-Search-API/assets/137120436/85d35f83-0df6-47c3-86e0-9713a4dbaf43)

</div>
</details>

<details>
<summary>DELETE実行</summary>
<div>
  
 ####
     curl --location --request DELETE 'http://localhost:8080/locations/outdoor-product'

![location_delete](https://github.com/Haruka2306/Location-Search-API/assets/137120436/f0588a0d-92a9-455d-9f30-e4cea39297c0)

</div>
</details>

<details>
<summary>DELETE実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/outdoor-product'

![location_delete-check](https://github.com/Haruka2306/Location-Search-API/assets/137120436/2c6e2576-b13d-47dd-854c-48e1a6f1c0b5)

</div>
</details>

### 今後の展望
* 登録、更新、削除機能の実装
* 自動テスト
* Spring Security

### 課題
お客様向け（取得機能のみ）、従業員向け（取得、登録、更新、削除機能）で機能を分ける必要がある。
