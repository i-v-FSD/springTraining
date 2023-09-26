# 研修課題

## イントロダクション

この研修では、JavaSilverの学習で培った基礎文法の知識を活用して、SpringBootによるWebアプリケーションの構築を行います。
SpringBootはJavaでWebアプリを構築する際に使われるフレームワークの1つで、実際の開発案件でも多く採用されています。
簡単なX（旧Twitter）クローンアプリの構築を通じて、フレームワークを使ったアプリ開発にチャレンジしてみましょう。
研修内でコーディングを進めていくにあたり、「何を書けば良いのか分からない...」と思い悩むことがあるかもしれません。その場合は
「何を作ろうとしているのか。」「どうやって作ろうとしているのか。」
を分けて考えると思考の整理がつきやすくなるので、意識してみてください。
それでは、がんばってください！

## 完成イメージ

   <video src="docs/media/AppSample.mp4" controls title="完成イメージ" width="1200px"></video>

## 目的

- 開発環境の構築に慣れる
- 重要な概念(MVCやCRUD、Git)の基本習得
- FWの概念に触れる
- 実際に手を動かしてみる

## 実装機能

- DB操作（CRUD）
- バリデーション
- 例外処理

## 環境構築

1. WSL2
   1. WSL install
   2. Ubuntu install
   3. デフォルトユーザーをrootに設定
   4. JDK install
2. Docker-Desktop
   1. Docker-Desktop install
   2. DockerをWSL(Ubuntu)にアタッチ
3. VS Code
   1. 拡張機能を追加
      1. WSL
      2. Gradle for Java
      3. Spring Boot Extension Pack
      4. Extension Pack for Java
   2. WSLへのアタッチ
4. ソースコード取得
   下記GitHubリポジトリより取得
   - <https://github.com/createOrganizationPJ/springTraining>
5. 動作確認
   1. MySQLコンテナをビルド
   2. Springプロジェクト立ち上げ

### 参考資料

- [VS CodeでSpringBoot立ち上げ](https://www.i-vinci.co.jp/techblog/1074)
- [WindowsでWSL2+Dockerを使うための環境構築](https://qiita.com/minato-naka/items/84508472c04f628e576e)
- [Windows上のVS CodeでRemote-WSLを使い。WSL2のUbuntu 20.04へアクセスする。](https://zenn.dev/s_ryuuki/articles/4b9631674adea4)

### アプリ立ち上げ

1. コンテナ立ち上げ  
docker-compose.ymlが存在するディレクトリで実行  

   ```sh
   # 初回実行時
   docker-compose up -d --build
   # 2回目以降実行時
   docker-compose start
   ```

   ブランチ切り替えなどでコンテナが立ち上がらなくなった場合は以下を実行し、コンテナを削除  
   以降、1回目と同じ手順でコンテナ立ち上げ

   ```sh
   docker-compose rm db
   ```

1. Springアプリ実行
1. 画面表示  
<http://localhost:8080/>

## 実装手順

1. ルーティング実装　＜複数のファイル間でデータの受け渡し＞
   1. MVC説明
      - 参考記事 <https://system-kaihatu.com/archives/3204#:~:text=MVC%E3%83%A2%E3%83%87%E3%83%AB%E3%81%A8%E3%81%AF%E3%80%81%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%A0,%E5%9B%B3%E3%82%8B%E3%81%93%E3%81%A8%E3%81%8C%E3%81%A7%E3%81%8D%E3%81%BE%E3%81%99%E3%80%82>
   2. Controller・View実装
      - やること：ControllerからViewに文字列を渡し、画面に表示する
      1. Controller
         - 遷移先設定
         - Viewで表示する文字列を設定
      2. View
         - Controllerで設定した文字列を画面表示する
   3. Service実装
      - やること：ServiceからControllerに文字列を渡し、それをViewに渡して画面に表示する
      1. Service
         - 文字列を返すメソッドを追加
      2. Controller
         - Serviceの戻り値をViewへ渡すように変更
   4. Dao仮実装
      - やること：Dao → Service → Controller → Viewと文字列を渡していき、画面に表示する
      1. Dao
         - 文字列を返すメソッドを追加
      2. Service
         - Daoの戻り値をControllerへ渡すように変更
      3. Controller
         - Serviceの戻り値をViewへ渡すように変更
2. tweet表示機能
   1. Dao→データ取得したオブジェクトを返すメソッドに変更
      1. jdbcTemplateでselect文実行
   2. Service→Daoの戻り値をControllerへ渡すように変更
   3. Controller→Serviceの戻り値をViewへ渡すように変更
   4. View→Controllerで設定したオブジェクトを画面表示
3. tweet投稿機能
4. tweet削除機能
5. tweet編集機能
   1. 編集用画面追加
      1. 編集対象のレコード取得
      2. 編集対象を画面表示
6. ルーティング変更
   1. 遷移先指定をリダイレクト
7. スタイル追加
   1. 見やすい程度にCSS追加・適用
8. データ型変換
   1. Entityクラス追加（Tweet型）
   2. Select、update処理をEntityのデータ型に変換
9. 例外処理追加
10. バリデーション（入力チェック）
11. リファクタリング
    1. 重複処理の削除
    2. 重複値を定数化
    3. メッセージファイル追加（定数 or Enum）

## メンター説明

1. ゴールの認識合わせ
2. 1日の作業の進め方
   1. git pushタイミング
      1. エラーが出ていないタイミング
      2. 最低1日1回
3. コーディングの進め方
   1. コーディングチェックポイントの設定
   2. 動作確認の必要性（画面/デバッグモードによる確認）
   3. 各種命名
   4. フォーマット（インデントなど）
WindowsでDockerを使う時、正しくファイル配置しないと激重になるので注意 - Qiita
概要WindowsでDockerを使う時、Windowsの設定、Dockerの設定、アプリコードの配置などを正しい状態にしておかないとアプリがすごく重い状態になってしまいます。正しく設定す…
