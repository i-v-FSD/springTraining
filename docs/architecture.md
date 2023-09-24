# architecture

Java Silver取得と並行、もしくは後続で実施する前提で、Java学習に絞ったアプリケーションです。  
開発環境は環境構築やコマンドラインへの拒絶反応の緩和を目的に作っているため、Spring bootアプリケーションのベストプラクティスには準拠していません。

## インフラ・コード管理ツール

- WSL2
- Docker-Desktop
- Visual Studio Code（拡張機能含む）
- Git

## サーバーサイド

フレームワークとして[Spring boot](https://spring.io/projects/spring-boot)を利用します。
ただし、今回の学習目的に併せて、フレームワーク固有の機能はできるだけ使わず、ピュアなJavaに近しい形で実装を進めます。

DBとの接続情報は設定ファイルに記載しています。

- tweet/src/main/resources/application.properties

## クライアントサイド

テンプレートエンジン[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf_ja.html)を使用します。
HTML、Javaの基本を押さえておけば、記述可能です。  
スタイル（CSSやJavaScript）についてはスコープ外のため、開発過程で画面が見づらい場合にCSSを追加するレベル感です。

## データベース

[MySQL](https://www.mysql.com/jp/)をdockerコンテナ上で動かしています。
MySQLのバージョンを上げても問題ない想定ですが、上手くいかない場合`character set`を確認してみてください。

環境周りの情報については以下を参照してください

- tweet/docker-compose.yml
  - データベース設定を記載
- tweet/mysql/init/create_db.sql
  - テーブル定義などを記載

|||
|--|--|
|使用製品|MySQL5.7|
|データベース名|twitter|
|テーブル|tweet<br>user|

### テーブル定義

#### tweetテーブル

本開発のメインで使用します。

|物理名|data type|NOT NULL|Other specifications|uniq|
|---|---|---|---|---|
|id|MEDIUMINT|yes|AUTO_INCREMENT|yes|
|user_id|INT|yes|||
|content|VARCHAR(140)|yes|||
|create_at|TIMESTAMP||CURRENT_TIMESTAMP||
|updated_at|TIMESTAMP||CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP||

#### userテーブル

本開発では使用しません。ユーザー認証機能を実装する可能性を見越して追加しています。

|物理名|data type|NOT NULL|Other specifications|uniq|
|---|---|---|---|---|
|id|MEDIUMINT|yes|AUTO_INCREMENT|yes|
|name|VARCHAR(255)|yes|||
|password|VARCHAR(255)|yes|||
|create_at|TIMESTAMP||CURRENT_TIMESTAMP||
|updated_at|TIMESTAMP||CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP||
