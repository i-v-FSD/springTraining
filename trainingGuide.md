# 研修課題

## 目的

- 開発環境の構築に慣れる
- CRUDの基本習得
- FWの概念に触れる
- 実際に手を動かす

## やること

Spring Bootで以下の機能を作成する

- Tweet投稿（C）
- Tweet表示（R）
- Tweet編集（U）
- Tweet削除（D）
- 余裕があれば、ログイン・ログアウト機能

## 環境構築

1. WSL2
   1. WSL install
   2. Ubuntu install
   3. デフォルトユーザーをrootに設定
   4. JDK install
2. Docker-Desktop（GUIもあり不慣れでも扱いやすさがメリット）
   1. Docker-Desktop install
   2. DockerをWSL(Ubuntu)にアタッチ
3. VSCode
   1. 拡張機能を追加
      1. WSL
      2. Gradle for Java
      3. Spring Boot Extension Pack
      4. Extension Pack for Java
   2. WSLへのアタッチ
4. ソースコード取得
5. 動作確認
   1. MySQLコンテナをビルド
   2. Springプロジェクト立ち上げ

### 参考資料

- [WindowsでWSL2+Dockerを使うための環境構築](https://qiita.com/minato-naka/items/84508472c04f628e576e)
- [Windows上のVS CodeでRemote-WSLを使い。WSL2のUbuntu 20.04へアクセスする。](https://zenn.dev/s_ryuuki/articles/4b9631674adea4)

### アプリ立ち上げ

1. コンテナ立ち上げ  
docker-compose.ymlが存在するディレクトリで実行  

   ```sh
   # 初回実行時
   docker-compose up -d build
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
