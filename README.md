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
![Location-Search-API drawio (2)](https://github.com/Haruka2306/Location-Search-API/assets/137120436/6c63ec64-3362-459e-aab5-e74e56e4499b)



## DB登録済みデータ
| id | corner | locationName | place | creator|
| ---- | ---- | ---- | ---- |----|
| 1 | food | A | left-back | yamada |
| 2 | drink | B | center-back | yamada |
| 3 | daily-necessities | C | right-back | yamada |
| 4 | electronic-appliances | D | left-front | yamada |
| 5 | outdoor-product | E | center-front | yamada |
| 6 | toy | F | right-front | yamada |


## 動作確認
* 検索したcorner名に紐づいたlocationNameとplaceを取得する。
####
     curl --location 'http://localhost:8080/location-search?corner=food'
![location-search-get200](https://github.com/Haruka2306/Location-Search-API/assets/137120436/96ac3c34-dab7-433c-b988-c0d847488ab2)

* corner名が空白の場合、400 BadRequestを返す
####
     curl --location 'http://localhost:8080/location-search?corner='
![location-search-get-NotBlank400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/07ee3026-cfd3-4004-8a17-47cba81c67d7)

* DBに登録されていないcorner名が検索された場合、400 BadRequestを返す
####
     curl --location 'http://localhost:8080/location-search?corner=i'
![location-search-get-CustomException400](https://github.com/Haruka2306/Location-Search-API/assets/137120436/f471b8da-b559-4056-97b6-135f6c32e22e)

### 今後の展望
* 登録、更新、削除機能の実装
* 自動テスト
* Spring Security

### 課題
お客様向け（取得機能のみ）、従業員向け（取得、登録、更新、削除機能）で機能を分ける必要がある。
