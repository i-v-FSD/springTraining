# 環境構築

本アプリケーションで想定している開発環境について記載する。
カリキュラムとしては深く立ち入らないため、あとは個々人で学習すること。

## 本環境の意図

Windowsで開発を行う上で必要なツールに触れし、今後他の言語やFWなどで応用するための下地作りを目的に環境を整備している。
また、副次的な効果として、JavaSilverの学習（主にコマンドライン部）にも利用できると考えている。  
※SpringBootの開発環境のベストプラクティスを目指していない。

## 構築手順

[VS CodeでSpringBoot立ち上げ](https://www.i-vinci.co.jp/techblog/1074)を参考に環境構築を行うが、
<b style="color: red; ">
WSL、Docker、Visual Studio Codeのインストーラーは自社指定のものを使うこと（必須）。</b>

1. WSL2
   1. WSL install
      1. [インストーラー格納先](https://vinci01.sharepoint.com/:f:/s/staff_and_bp/Eg2BsFdrgBpBiYxPijcgsK8BDDFFBhR6usW_LT2Sqd8sHw?e=BTGwfG)
   2. Ubuntu install
   3. デフォルトユーザーをrootに設定
   4. JDK install
2. Docker-Desktop（GUIもあり不慣れでも扱いやすさがメリット）
   1. Docker-Desktop install
      1. [インストーラー格納先](https://vinci01.sharepoint.com/:f:/s/staff_and_bp/En3tPPwDcLhLhl_gNB86ys8BNOcAuX-cPScufnVF2g4Qvg?e=jwqpSU)
   2. DockerをWSL(Ubuntu)にアタッチ
3. Visual Studio Code
   1. エディタインストール
      1. [インストーラー格納先](https://vinci01.sharepoint.com/:f:/s/staff_and_bp/Es6u79Vf3MdEh1pYwIFP0ccBiZ9AzwUlGwTb6gtrP2McDg?e=QDyHyg)
   2. 拡張機能を追加
      1. WSL
      2. Gradle for Java
      3. Spring Boot Extension Pack
      4. Extension Pack for Java
   3. WSLへのアタッチ
4. ソースコード取得
5. 動作確認
   1. MySQLコンテナをビルド
   2. Springプロジェクト立ち上げ

## 参考資料

- [WindowsでWSL2+Dockerを使うための環境構築](https://qiita.com/minato-naka/items/84508472c04f628e576e)
- [Windows上のVS CodeでRemote-WSLを使い。WSL2のUbuntu 20.04へアクセスする。](https://zenn.dev/s_ryuuki/articles/4b9631674adea4)
