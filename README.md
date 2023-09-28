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
   - [GitHubリポジトリ](https://github.com/i-v-FSD/springTraining)
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

1. ルーティング実装　＜フロントとバック間のデータの受け渡し・クラス定義やメソッド戻り値の利用＞
   1. MVC説明
      - [MVCとは](https://system-kaihatu.com/archives/3204#:~:text=MVC%E3%83%A2%E3%83%87%E3%83%AB%E3%81%A8%E3%81%AF%E3%80%81%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%A0,%E5%9B%B3%E3%82%8B%E3%81%93%E3%81%A8%E3%81%8C%E3%81%A7%E3%81%8D%E3%81%BE%E3%81%99%E3%80%82)
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
         - Serviceの戻り値をViewへ渡す
   4. Dao仮実装
      - やること：Dao → Service → Controller → Viewと文字列を渡していき、画面に表示する
      1. Dao
         - 文字列を返すメソッドを追加
      2. Service
         - Daoの戻り値をControllerへ渡す
      3. Controller
         - Serviceの戻り値をViewへ渡す
2. ツイート表示機能
   - やること：DBからレコードを取得、画面に表示
   1. Dao
      - DBからデータ取得
      - 取得したデータをオブジェクトに格納
      - オブジェクトをServiceに渡す
   2. Service
      - Daoの戻り値をControllerへ渡す
   3. Controller
      - Serviceの戻り値をViewへ渡す
   4. View
      - Controllerで設定したオブジェクトを画面表示
3. ツイート投稿機能
   - やること：画面に入力されたデータを取得、DBに登録
   1. View
      - テキストボックス作成
      - 投稿ボタン追加
   2. Controller
      - 画面で投稿ボタンが押された時
         - 画面のテキストボックスの値を取得
         - Serviceの投稿用メソッドを実行
   3. Service
      - Daoの投稿用メソッドを実行
   4. Dao
      - 画面から取得したデータをDBに登録
4. ツイート削除機能
   - やること：削除ボタンが押されたレコードをDBから削除
   1. View
      - 削除ボタン追加
   2. Controller
      - 画面で削除ボタンが押された時
         - ボタンが押されたレコードのIDを取得
         - Serviceの削除用メソッドを実行
   3. Service
      - Daoの削除用メソッドを実行
   4. Dao
      - IDに対応するレコードをDBから削除
5. ツイート編集機能
   - やること：編集ボタンが押されたレコードの設定値を更新
   1. 編集用画面追加
      - 編集対象のレコード取得
      - 編集対象を画面表示
   2. View
      - 編集ボタンを追加
   3. Controller
      - 画面で編集ボタンが押された時
         - ボタンが押されたレコードのIDを取得
         - 編集画面に遷移
      - 編集画面で決定ボタンが押された時
         - 編集画面のテキストボックスの入力値を取得
         - Serviceの編集用メソッドを実行
   4. Service
      - Daoの削除用メソッドを実行
   5. Dao
      - IDに対応するレコードの設定値を、テキストボックスの入力値に更新
6. ルーティング変更
   1. 遷移先指定をリダイレクト
7. スタイル追加
   - やること：画面要素の配置を整え、見やすくする
   1. CSS追加・適用
8. データ型変換
   - やること：ツイートを格納する為のクラスを作る
   1. Entityクラス追加
      - ツイートが持つ個々のデータに対応する変数を定義
      - Entityクラスをカプセル化する
   2. DBからレコード取得時、Entityクラスのインスタンスに格納する様変更
9. 例外処理追加
   - 予期しない入力・出力がされた際にそれを検知し画面に通知を出す
   1. 下記の例外処理を追加
      - バリデーションに引っかかった場合（バリデーションは次項で実装）
      - DBアクセスに失敗した場合
      - DBのレコードを操作する処理をした際に変更されたレコードが無い場合
10. バリデーション（入力チェック）
   -画面に入力された値が入力規則に沿っているか確認する
   1. 画面の入力値が規則に沿っていない場合バリデーションの例外をスローし画面に通知を出す
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
