# Location-Search-API

## API概要
店舗で、探している商品が含まれるコーナー名を検索すると商品が置いてあるロケーション名とおおよその位置情報を知ることができます。

## 作成背景
勤務していた店舗では、毎週商品の位置が変更しており、お客様から商品の場所を聞かれることがかなり多かった。
また、従業員も毎週商品の位置を把握するのが大変だった。
そこで、商品の位置情報検索機能を持つ簡単なアプリを自作しようと思った。

## 使用技術
* Java 17
* Spring Boot 3.1.3
* MySQL 8.0.34
* Docker 24.0.5
  

## API概略図
![Location-Search-API drawio (6)](https://github.com/Haruka2306/Location-Search-API/assets/137120436/07d337e7-3d9e-4d03-811c-54009ce088c4)






## DB登録済みデータ
| id | corner | locationName | place | creator| dateCreated|
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
     curl --location --request POST 'http://localhost:8080/locations'

 ![task-final-post](https://github.com/Haruka2306/Location-Search-API/assets/137120436/8a56d973-3ff2-4f70-a714-de05e17a2888)
 
</div>
</details>

<details>
<summary>POST実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/game'

 ![task-final-post確認](https://github.com/Haruka2306/Location-Search-API/assets/137120436/804fba8e-7acd-4b0b-963f-1b4e4a0f6b50)

</div>
</details>

<details>
<summary>PATCH実行</summary>
<div>
  
 ####
     curl --location --request PATCH 'http://localhost:8080/locations/toy'

![task-final-patch](https://github.com/Haruka2306/Location-Search-API/assets/137120436/c13d418d-65b6-42cf-ac19-f5dec8dbea19)

</div>
</details>

<details>
<summary>PATCH実行確認</summary>
<div>
  
 ####
     curl --location 'http://localhost:8080/locations/toy'

![task-final-patch確認](https://github.com/Haruka2306/Location-Search-API/assets/137120436/2a6087a3-2c3f-4d81-ba23-cf78f38140fe)

</div>
</details>

### 今後の展望
* 登録、更新、削除機能の実装
* 自動テスト
* Spring Security

### 課題
お客様向け（取得機能のみ）、従業員向け（取得、登録、更新、削除機能）で機能を分ける必要がある。
