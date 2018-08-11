/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 検索した値、結果を保持するクラス
 * @author sumi3
 */
public class SearchResult implements Serializable{
    private String keyword;//検索したキーワード(検索結果画面でも表示)
    private String b_cateId;//カテゴリIDの大分類
    private String categoryId;//カテゴリID(intでも良いと思うが一応String)(検索結果画面でも表示)
    private String sort;//ソート順(検索結果画面でも表示)
    private String highPrice;//最高金額で検索した数字(String)(検索結果画面でも表示)
    private String lowPrice;//最低金額で検索した数字(String)(検索結果画面でも表示)
    private int hitCount;//検索結果にヒットしたアイテム数(検索結果画面で表示)
    private ArrayList<String> names = new ArrayList<String>();//商品名
    private ArrayList<String> description = new ArrayList<String>();//商品説明
    private ArrayList<String> thumnail = new ArrayList<String>();//商品サムネイル画像
    private ArrayList<String> largeThumnail = new ArrayList<String>();//商品画像300
    private ArrayList<Integer> price = new ArrayList<Integer>();//商品価格
    private ArrayList<String> rate = new ArrayList<String>();//商品評価点
    private ArrayList<String> itemCode = new ArrayList<String>();//商品コード
    
    
    
    public SearchResult(){
        this.keyword = "";
        this.b_cateId = "";
        this.categoryId = "";
        this.sort = "";
        this.highPrice = "";
        this.lowPrice = "";
    }
    

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }
    public String getKeyword(){
        return keyword;
    }
    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }
    public String getCategoryId(){
        return categoryId;
    }
    public void setB_cateId(String b_cateId){
        this.b_cateId = b_cateId;
    }
    public String getB_cateId(){
        return b_cateId;
    }
    public void setSort(String sort){
        this.sort = sort;
    }
    public String getSort(){
        return sort;
    }
    public void setHighPrice(String price){
        this.highPrice = price;
    }
    public String getHighPrice(){
        return highPrice;
    }
    public void setLowPrice(String price){
        this.lowPrice = price;
    }
    public String getLowPrice(){
        return lowPrice;
    }
    public void setHitCount(int count){
        this.hitCount = count;
    }
    public int getHitCount(){
        return hitCount;
    }
    public void setName(String name){
        this.names.add(name);
    }
    public String getName(int number){
        return names.get(number);
    }
    public void setDescription(String des){
        this.description.add(des);
    }
    public String getDescription(int number){
        return description.get(number);
    }
    public void setThumnail(String thumnail){
        this.thumnail.add(thumnail);
    }
    public String getThumnail(int number){
        return thumnail.get(number);
    }
    public void setLargeThumnail(String lthum){
        this.largeThumnail.add(lthum);
    }
    public String getLargeThumnail(int number){
        return largeThumnail.get(number);
    }
    public void setPrice(int price){
        this.price.add(price);
    }
    public int getPrice(int number){
        return price.get(number);
    }
    public void setRate(String rate){
        this.rate.add(rate);
    }
    public String getRate(int number){
        return rate.get(number);
    }
    public void setItemCode(String code){
        this.itemCode.add(code);
    }
    public String getItemCode(int number){
        return itemCode.get(number);
    }
    public int getItemSize(){
        return this.itemCode.size();
    }
    public ArrayList<String> getCategoryName(){
        ArrayList<String> category = new ArrayList<String>();
        switch(this.b_cateId){
            case "":
                switch(this.categoryId){
                    case "1":
                        category.add("すべてのカテゴリ");
                        break;
                    case "13457":
                        category.add("ファッション");
                        break;
                    case "2498":
                        category.add("食品");
                        break;
                    case "2513":
                        category.add("アウトドア、釣り、旅行用品");
                        break;
                    case "2500":
                        category.add("ダイエット、健康");
                        break;
                    case "2501":
                        category.add("コスメ、美容、ヘアケア");
                        break;
                    case "2502":
                        category.add("スマホ、タブレット、パソコン");
                        break;
                    case "2504":
                        category.add("テレビ、オーディオ、カメラ");
                        break;
                    case "2505":
                        category.add("家電");
                        break;
                    case "2506":
                        category.add("家具、インテリア");
                        break;
                    case "2507":
                        category.add("花、ガーデニング");
                        break;
                    case "2508":
                        category.add("キッチン、日用品、文具");
                        break;
                    case "2503":
                        category.add("DIY、工具、ガーデニング");
                        break;
                    case "2509":
                        category.add("ペット用品、生き物");
                        break;
                    case "2510":
                        category.add("楽器、手芸、コレクション");
                        break;
                    case "2511":
                        category.add("ゲーム、おもちゃ");
                        break;
                    case "2497":
                        category.add("ベビー、キッズ、マタニティ");
                        break;
                    case "2512":
                        category.add("スポーツ");
                        break;
                    case "2514":
                        category.add("車、バイク、自転車");
                        break;
                    case "2516":
                        category.add("CD、音楽ソフト");
                        break;
                    case "2517":
                        category.add("DVD、映像ソフト");
                        break;
                    case "10002":
                        category.add("本、雑誌、コミック");
                        break;
                    case "47727":
                        category.add("レンタル、各種サービス");
                        break;    
                }
                break;
            case "13457":
                category.add("ファッション");
                switch(this.categoryId){
                    case "2494":
                        category.add("レディースファッション");
                        break;
                    case "2495":
                        category.add("メンズファッション");
                        break;
                    case "2496":
                        category.add("腕時計、アクセサリー");
                        break;
                }
                break;
            case "2498":
                category.add("食品");
                switch(this.categoryId){
                    case "2499":
                        category.add("ドリンク、水、お酒");
                        break;
                    case "4744":
                        category.add("スイーツ、洋菓子");
                        break;
                    case "1167":
                        category.add("和菓子、中華菓子");
                        break;
                    case "4745":
                        category.add("スナック、お菓子、おつまみ");
                        break;
                    case "1234":
                        category.add("米、雑穀、粉類");
                        break;
                    case "939":
                        category.add("魚介類、海産物");
                        break;
                    case "998":
                        category.add("肉、ハム、ソーセージ");
                        break;
                    case "1034":
                        category.add("フルーツ");
                        break;
                    case "1032":
                        category.add("野菜");
                        break;
                    case "1201":
                        category.add("麺類、パスタ");
                        break;
                    case "1184":
                        category.add("パン、シリアル");
                        break;
                    case "1263":
                        category.add("チーズ、乳製品、卵");
                        break;
                    case "15015":
                        category.add("惣菜、料理");
                        break;
                    case "4743":
                        category.add("漬物、佃煮、ふりかけ");
                        break;
                    case "41023":
                        category.add("豆腐、納豆、こんにゃく");
                        break;
                    case "1071":
                        category.add("乾物、乾燥豆類、缶詰");
                        break;
                    case "1273":
                        category.add("調味料、料理の素、油");
                        break;
                    case "1313":
                        category.add("製菓材料、パン材料");
                        break;
                    case "41952":
                        category.add("非常用食品");
                        break;
                    case "1448":
                        category.add("その他食品");
                        break;
                }
                break;
            case "2513":
                category.add("アウトドア、釣り、旅行用品");
                switch(this.categoryId){
                    case "2660":
                        category.add("釣り");
                        break;
                    case "2522":
                        category.add("アウトドア、キャンプ、登山");
                        break;
                    case "2938":
                        category.add("カヌー、カヤック、ボート");
                        break;
                    case "3566":
                        category.add("プレジャーボート、ヨット");
                        break;
                    case "4104":
                        category.add("旅行用品");
                        break;
                    case "4105":
                        category.add("海外おみやげ");
                        break;
                    case "2949":
                        category.add("その他アウトドア用品");
                        break;
                }
                break;
            case "2500":
                category.add("ダイエット、健康");
                switch(this.categoryId){
                    case "2022":
                        category.add("コンタクトレンズ、ケア用品");
                        break;
                    case "16003":
                        category.add("メガネ、老眼鏡");
                        break;
                    case "48951":
                        category.add("補聴器");
                        break;
                    case "1948":
                        category.add("ダイエット");
                        break;
                    case "45230":
                        category.add("マッサージ、リラクゼーション");
                        break;
                    case "45229":
                        category.add("アロマグッズ");
                        break;
                    case "45231":
                        category.add("冷え対策、保温グッズ");
                        break;
                    case "45232":
                        category.add("暑さ対策、冷却グッズ");
                        break;
                    case "45489":
                        category.add("花粉症対策グッズ");
                        break;
                    case "45492":
                        category.add("電子タバコ、禁煙グッズ");
                        break;
                    case "45493":
                        category.add("いびき防止グッズ");
                        break;
                    case "14582":
                        category.add("健康アクセサリー");
                        break;
                    case "1874":
                        category.add("健康飲料");
                        break;
                    case "45564":
                        category.add("特定保健用食品(トクホ)");
                        break;
                    case "45224":
                        category.add("バランス栄養、栄養調整食品");
                        break;
                    case "4749":
                        category.add("サプリメント");
                        break;
                    case "2053":
                        category.add("医薬品、医薬部外品");
                        break;
                    case "34752":
                        category.add("鍼、灸、治療器");
                        break;
                    case "34747":
                        category.add("矯正用品、補助ベルト");
                        break;
                    case "45898":
                        category.add("健康管理、計測計");
                        break;
                    case "45945":
                        category.add("救急用品");
                        break;
                    case "2004":
                        category.add("衛生日用品");
                        break;
                    case "2012":
                        category.add("オーラルケア");
                        break;
                    case "2052":
                        category.add("介護用品");
                        break;
                    case "2117":
                        category.add("その他ダイエット、健康");
                        break;
                }
                break;
            case "2501":
                category.add("コスメ、美容、ヘアケア");
                switch(this.categoryId){
                    case "1752":
                        category.add("スキンケア、基礎化粧品");
                        break;
                    case "1791":
                        category.add("ベースメイク");
                        break;
                    case "13709":
                        category.add("コフレ");
                        break;
                    case "1772":
                        category.add("メイクアップ");
                        break;
                    case "48912":
                        category.add("まつ毛、つけまつ毛用品");
                        break;
                    case "46356":
                        category.add("メイク道具");
                        break;
                    case "1809":
                        category.add("ネイル");
                        break;
                    case "1803":
                        category.add("香水");
                        break;
                    case "46328":
                        category.add("フェイスケア");
                        break;
                    case "1841":
                        category.add("ボディケア");
                        break;
                    case "46427":
                        category.add("日焼け止め");
                        break;
                    case "46433":
                        category.add("脱毛、除毛");
                        break;
                    case "46467":
                        category.add("制汗剤、デオドラント");
                        break;
                    case "1831":
                        category.add("レディースヘアケア");
                        break;
                    case "46714":
                        category.add("メンズヘアケア");
                        break;
                    case "46737":
                        category.add("メンズシェービング用品");
                        break;
                    case "1849":
                        category.add("メンズスキンケア、メイク");
                        break;
                }
                break;
            case "2502":
                category.add("スマホ、タブレット、パソコン");
                switch(this.categoryId){
                    case "38338":
                        category.add("スマホ");
                        break;
                    case "21076":
                        category.add("タブレットPC");
                        break;
                    case "38346":
                        category.add("スマホ、タブレットアクセサリー、周辺機器");
                        break;
                    case "36496":
                        category.add("スマートウォッチ、ウェアラブル端末");
                        break;
                    case "14242":
                        category.add("ノートパソコン");
                        break;
                    case "14241":
                        category.add("デスクトップパソコン");
                        break;
                    case "49351":
                        category.add("ディスプレイ、モニター");
                        break;
                    case "39624":
                        category.add("プリンター、複合機");
                        break;
                    case "2519":
                        category.add("パソコン周辺機器");
                        break;
                    case "88":
                        category.add("PCパーツ");
                        break;
                    case "113":
                        category.add("PCサプライ、アクセサリー");
                        break;
                    case "150":
                        category.add("ソフトウェア");
                        break;
                    case "38418":
                        category.add("携帯電話");
                        break;
                    case "4692":
                        category.add("その他");
                        break;
                }
                break;
            case "2504":
                category.add("テレビ、オーディオ、カメラ");
                switch(this.categoryId){
                    case "635":
                        category.add("テレビ、映像機器");
                        break;
                    case "660":
                        category.add("オーディオ機器");
                        break;
                    case "2443":
                        category.add("カメラ");
                        break;
                    case "2260":
                        category.add("望遠鏡、光学機器");
                        break;
                }
                break;
            case "2505":
                category.add("家電");
                switch(this.categoryId){
                    case "4740":
                        category.add("冷暖房器具、空調家電");
                        break;
                    case "5300":
                        category.add("生活家電");
                        break;
                    case "587":
                        category.add("キッチン家電");
                        break;
                    case "1987":
                        category.add("美容家電");
                        break;
                    case "1919":
                        category.add("健康家電");
                        break;
                    case "5301":
                        category.add("情報家電");
                        break;
                    case "577":
                        category.add("電池、充電池");
                        break;
                    }
                break;
            case "2506":
                category.add("家具、インテリア");
                switch(this.categoryId){
                    case "3664":
                        category.add("テレビ台、キャビネット");
                        break;
                    case "49282":
                        category.add("ラック、シェルフ、本棚");
                        break;
                    case "49277":
                        category.add("チェスト、衣類収納");
                        break;
                    case "49313":
                        category.add("プラケース、押入れ収納");
                        break;
                    case "49297":
                        category.add("キッチン収納");
                        break;
                    case "49292":
                        category.add("ランドリー、サニタリー収納");
                        break;
                    case "14709":
                        category.add("ソファ、ソファベッド");
                        break;
                    case "3646":
                        category.add("テーブル");
                        break;
                    case "3618":
                        category.add("椅子、スツール、座椅子");
                        break;
                    case "3640":
                        category.add("デスク、机");
                        break;
                    case "27409":
                        category.add("パーテーション、衝立");
                        break;
                    case "534":
                        category.add("照明、電球");
                        break;
                    case "3653":
                        category.add("ベッド、マットレス");
                        break;
                    case "3669":
                        category.add("布団、寝具");
                        break;
                    case "49218":
                        category.add("こたつ");
                        break;
                    case "3604":
                        category.add("カーテン、ブラインド");
                        break;
                    case "3610":
                        category.add("カーペット、ラグ、マット");
                        break;
                    case "4764":
                        category.add("クッション、座布団");
                        break;
                    case "48111":
                        category.add("ファブリック、カバー類");
                        break;
                    case "3587":
                        category.add("インテリア時計");
                        break;
                    case "47938":
                        category.add("ウォールデコレーション");
                        break;
                    case "3585":
                        category.add("インテリア雑貨");
                        break;
                    case "28069":
                        category.add("ミラー、ドレッサー");
                        break;
                    case "49305":
                        category.add("玄関家具");
                        break;
                    case "49177":
                        category.add("子供部屋家具");
                        break;
                    case "4355":
                        category.add("オフィス家具");
                        break;
                }
                break;
            case "2507":
                category.add("花、ガーデニング");
                switch(this.categoryId){
                    case "15627":
                        category.add("生花");
                        break;
                    case "4101":
                        category.add("プリザーブドフラワー");
                        break;
                    case "4087":
                        category.add("ドライフラワー");
                        break;
                    case "50159":
                        category.add("ハーバリウム");
                        break;
                    case "4767":
                        category.add("フラワーアレンジメント");
                        break;
                    case "15629":
                        category.add("リース");
                        break;
                    case "5235":
                        category.add("造花、アートフラワー");
                        break;
                    case "50187":
                        category.add("サボテン、多肉植物");
                        break;
                    case "20258":
                        category.add("観葉植物");
                        break;
                    case "50184":
                        category.add("人工観葉、フェイクグリーン");
                        break;
                    case "50185":
                        category.add("人工芝");
                        break;
                    case "15641":
                        category.add("盆栽、苔玉");
                        break;
                    case "4081":
                        category.add("種、種子");
                        break;
                    case "4068":
                        category.add("球根、種芋");
                        break;
                    case "4088":
                        category.add("苗");
                        break;
                    case "38288":
                        category.add("苗木、植木");
                        break;
                    case "15632":
                        category.add("水生植物");
                        break;
                    case "50191":
                        category.add("花瓶、花台");
                        break;
                    case "15631":
                        category.add("アレンジメント用品、資材");
                        break;
                    case "4036":
                        category.add("園芸用品");
                        break;
                    case "4103":
                        category.add("その他花、ガーデニング");
                        break;
                }
                break;
            case "2508":
                category.add("キッチン、日用品、文具");
                switch(this.categoryId){
                    case "4874":
                        category.add("キッチン、台所用品");
                        break;
                    case "49617":
                        category.add("スリッパ");
                        break;
                    case "46310":
                        category.add("タオル");
                        break;
                    case "46603":
                        category.add("バス、洗面所用品");
                        break;
                    case "46311":
                        category.add("トイレ用品");
                        break;
                    case "3955":
                        category.add("洗濯用品");
                        break;
                    case "49254":
                        category.add("ゴミ箱、ダストボックス");
                        break;
                    case "3968":
                        category.add("掃除用具");
                        break;
                    case "3689":
                        category.add("芳香剤、消臭剤、除湿剤");
                        break;
                    case "49596":
                        category.add("蚊取り、防虫、害虫駆除");
                        break;
                    case "46566":
                        category.add("文具、ステーショナリー");
                        break;
                    case "4222":
                        category.add("防災、防犯、セーフティ");
                        break;
                    case "4180":
                        category.add("冠婚葬祭、宗教用品");
                        break;
                    case "4150":
                        category.add("カタログギフト");
                        break;
                    case "4151":
                        category.add("ギフト券");
                        break;
                    case "4249":
                        category.add("その他キッチン、日用品、文具");
                        break;
                }
                break;
            case "2503":
                category.add("DIY、工具、ガーデニング");
                switch(this.categoryId){
                    case "50266":
                        category.add("庭、ガーデニング");
                        break;
                    case "4237":
                        category.add("住宅設備");
                        break;
                    case "3930":
                        category.add("道具、工具");
                        break;
                    case "38095":
                        category.add("材料、部品");
                        break;
                    case "38093":
                        category.add("業務、産業用");
                        break;
                    case "4442":
                        category.add("オフィス用品");
                        break;
                    case "3937":
                        category.add("その他DIY、業務、産業用品");
                        break;
                }
                break;
            case "2509":
                category.add("ペット用品、生き物");
                switch(this.categoryId){
                    case "4772":
                        category.add("犬用品");
                        break;
                    case "4820":
                        category.add("猫用品");
                        break;
                    case "4798":
                        category.add("熱帯魚、アクアリウム用品");
                        break;
                    case "4816":
                        category.add("鳥用品");
                        break;
                    case "32938":
                        category.add("小動物用品");
                        break;
                    case "33905":
                        category.add("昆虫用品");
                        break;
                    case "33966":
                        category.add("爬虫類、両生類用品");
                        break;
                    case "32533":
                        category.add("ペットフード");
                        break;
                    case "34003":
                        category.add("メモリアル、オーナーズグッズ");
                        break;
                    case "13588":
                        category.add("生き物、生体");
                        break;
                    case "49119":
                        category.add("動物用医薬品");
                        break;
                    case "4842":
                        category.add("その他ペット用品、生き物");
                        break;
                    }
                break;
            case "2510":
                category.add("楽器、手芸、コレクション");
                switch(this.categoryId){
                    case "2327":
                        category.add("楽器、器材");
                        break;
                    case "2266":
                        category.add("手芸、ハンドクラフト");
                        break;
                    case "2406":
                        category.add("コレクション、趣味");
                        break;
                    case "2191":
                        category.add("画材、アート用品");
                        break;
                    case "2422":
                        category.add("美術、工芸品");
                        break;
                    case "14828":
                        category.add("コスプレ衣装");
                        break;
                    case "2308":
                        category.add("実験、工作");
                        break;
                    case "4848":
                        category.add("占い、開運");
                        break;
                    case "2326":
                        category.add("その他楽器、手芸、コレクション");
                        break;
                    }
                break;
            case "2511":
                category.add("ゲーム、おもちゃ");
                switch(this.categoryId){
                    case "2161":
                        category.add("テレビゲーム");
                        break;
                    case "2119":
                        category.add("おもちゃ");
                        break;
                    case "15160":
                        category.add("フィギュア");
                        break;
                    case "2318":
                        category.add("ラジコン");
                        break;
                    case "2156":
                        category.add("ミニカー");
                        break;
                    case "17157":
                        category.add("食玩、プライズ、カプセル");
                        break;
                    case "2309":
                        category.add("模型、プラモデル");
                        break;
                    case "38146":
                        category.add("パズル");
                        break;
                    case "2169":
                        category.add("ボードゲーム");
                        break;
                    case "2420":
                        category.add("トレーディングカード");
                        break;
                    case "2165":
                        category.add("カードゲーム");
                        break;
                    case "4011":
                        category.add("パーティグッズ");
                        break;
                    case "37865":
                        category.add("乗用玩具");
                        break;
                    case "37853":
                        category.add("季節玩具");
                        break;
                    case "2166":
                        category.add("ダーツ");
                        break;
                    case "2168":
                        category.add("ビリヤード");
                        break;
                    case "44818":
                        category.add("囲碁、将棋、麻雀");
                        break;
                    case "2492":
                        category.add("その他おもちゃ");
                        break;
                    }
                break;
            case "2497":
                category.add("ベビー、キッズ、マタニティ");
                switch(this.categoryId){
                    case "50208":
                        category.add("衛生用品、ヘルスケア");
                        break;
                    case "41303":
                        category.add("おむつ、トイレ用品");
                        break;
                    case "41603":
                        category.add("お風呂、バス用品");
                        break;
                    case "41607":
                        category.add("授乳、食事用品");
                        break;
                    case "4251":
                        category.add("ベビー服、シューズ");
                        break;
                    case "4738":
                        category.add("ベビー用寝具、ベッド");
                        break;
                    case "48687":
                        category.add("ベビー用家具");
                        break;
                    case "48688":
                        category.add("ベビー用セーフティグッズ");
                        break;
                    case "48678":
                        category.add("抱っこ紐、おんぶ紐");
                        break;
                    case "48679":
                        category.add("スリング");
                        break;
                    case "4263":
                        category.add("ベビーカー");
                        break;
                    case "14937":
                        category.add("ベビーシート、チャイルドシート");
                        break;
                    case "41418":
                        category.add("おもちゃ、教材");
                        break;
                    case "4737":
                        category.add("子ども服、シューズ");
                        break;
                    case "48689":
                        category.add("子ども用寝具、ベッド");
                        break;
                    case "41763":
                        category.add("子ども用家具");
                        break;
                    case "41190":
                        category.add("バッグ、ランドセル");
                        break;
                    case "43965":
                        category.add("子ども用セーフティグッズ");
                        break;
                    case "43995":
                        category.add("子ども用自転車、三輪車");
                        break;
                    case "25317":
                        category.add("マタニティウエア");
                        break;
                    case "48730":
                        category.add("授乳、産後ウェア");
                        break;
                    case "4739":
                        category.add("マタニティ、産後用品");
                        break;
                    case "13618":
                        category.add("出産祝い、出産記念品");
                        break;
                    case "14936":
                        category.add("記念、行事用品");
                        break;
                    case "4353":
                        category.add("その他ベビー、キッズ、マタニティ用品");
                        break;
                    }
                break;
            case "2512":
                category.add("スポーツ");
                switch(this.categoryId){
                    case "3059":
                        category.add("ゴルフ");
                        break;
                    case "2986":
                        category.add("スノーボード");
                        break;
                    case "2964":
                        category.add("スキー");
                        break;
                    case "2950":
                        category.add("フィットネス、トレーニング");
                        break;
                    case "14894":
                        category.add("マラソン、ランニング");
                        break;
                    case "3336":
                        category.add("野球");
                        break;
                    case "3113":
                        category.add("サッカー、フットサル");
                        break;
                    case "3258":
                        category.add("テニス");
                        break;
                    case "3280":
                        category.add("バスケットボール");
                        break;
                    case "3295":
                        category.add("バレーボール");
                        break;
                    case "3227":
                        category.add("ソフトボール");
                        break;
                    case "3287":
                        category.add("バドミントン");
                        break;
                    case "3244":
                        category.add("卓球");
                        break;
                    case "3358":
                        category.add("ラグビー");
                        break;
                    case "3370":
                        category.add("陸上、トラック、フィールド");
                        break;
                    case "16308":
                        category.add("ヨガ、ピラティス");
                        break;
                    case "3001":
                        category.add("武道、格闘技");
                        break;
                    case "14757":
                        category.add("ダンス、バレエ");
                        break;
                    case "3234":
                        category.add("新体操、器械体操");
                        break;
                    case "3212":
                        category.add("ストリート系スポーツ");
                        break;
                    case "3305":
                        category.add("マリンスポーツ");
                        break;
                    case "3188":
                        category.add("水泳");
                        break;
                    case "46557":
                        category.add("コンプレッションウエア");
                        break;
                    case "46567":
                        category.add("スポーツ用下着（汎用）");
                        break;
                    case "46625":
                        category.add("スポーツアクセサリー");
                        break;
                    case "46578":
                        category.add("スポーツバッグ（汎用）");
                        break;
                    case "46616":
                        category.add("スポーツケア用品");
                        break;
                    case "2952":
                        category.add("体育器具、用品");
                        break;
                    case "16429":
                        category.add("その他の競技種目");
                        break;
                    }
                break;
            case "2514":
                category.add("車、バイク、自転車");
                switch(this.categoryId){
                    case "41234":
                        category.add("自動車");
                        break;
                    case "41235":
                        category.add("バイク");
                        break;
                    case "3174":
                        category.add("自転車");
                        break;
                }
                break;
            case "2516":
                category.add("CD、音楽ソフト");
                switch(this.categoryId){
                    case "721":
                        category.add("邦楽");
                        break;
                    case "739":
                        category.add("洋楽");
                        break;
                    case "15718":
                        category.add("KーPOP");
                        break;
                    case "761":
                        category.add("ワールドミュージック");
                        break;
                    case "5401":
                        category.add("アニメ、ゲーム");
                        break;
                    case "772":
                        category.add("キッズ、ファミリー");
                        break;
                    case "726":
                        category.add("インディーズ");
                        break;
                    case "748":
                        category.add("クラシック");
                        break;
                    case "744":
                        category.add("ジャズ、フュージョン");
                        break;
                    case "5388":
                        category.add("ヒーリング、ニューエイジ");
                        break;
                    case "5394":
                        category.add("サウンドトラック");
                        break;
                    case "5403":
                        category.add("インストゥルメンタル");
                        break;
                    case "846":
                        category.add("実用");
                        break;
                    case "851":
                        category.add("その他");
                        break;
                    case "16509":
                        category.add("チケット");
                        break;
                    }
                break;
            case "2517":
                category.add("DVD、映像ソフト");
                switch(this.categoryId){
                    case "868":
                        category.add("邦画");
                        break;
                    case "853":
                        category.add("洋画");
                        break;
                    case "881":
                        category.add("アニメーション");
                        break;
                    case "901":
                        category.add("ミュージック");
                        break;
                    case "884":
                        category.add("テレビドラマ");
                        break;
                    case "5608":
                        category.add("お笑い、バラエティ");
                        break;
                    case "900":
                        category.add("アイドル、イメージ");
                        break;
                    case "908":
                        category.add("スポーツ、フィットネス");
                        break;
                    case "2469":
                        category.add("キッズ、ファミリー");
                        break;
                    case "5615":
                        category.add("演劇、ミュージカル");
                        break;
                    case "920":
                        category.add("趣味、実用、教養");
                        break;
                    case "937":
                        category.add("その他");
                        break;
                    }
                break;
            case "10002":
                category.add("本、雑誌、コミック");
                switch(this.categoryId){
                    case "10251":
                        category.add("コミック、アニメ");
                        break;
                    case "12492":
                        category.add("文芸");
                        break;
                    case "10003":
                        category.add("雑誌");
                        break;
                    case "10141":
                        category.add("アイドル写真集");
                        break;
                    case "10725":
                        category.add("ビジネス、経済");
                        break;
                    case "10604":
                        category.add("コンピュータ");
                        break;
                    case "10149":
                        category.add("エンターテインメント");
                        break;
                    case "11820":
                        category.add("子ども");
                        break;
                    case "12243":
                        category.add("生活");
                        break;
                    case "11998":
                        category.add("趣味");
                        break;
                    case "11505":
                        category.add("芸術");
                        break;
                    case "13190":
                        category.add("歴史、心理、教育");
                        break;
                    case "11270":
                        category.add("学習参考書");
                        break;
                    case "11676":
                        category.add("語学、辞書");
                        break;
                    case "10953":
                        category.add("医学、薬学、看護");
                        break;
                    case "10209":
                        category.add("ゲーム攻略本");
                        break;
                    case "12425":
                        category.add("地図、ガイド");
                        break;
                    case "12204":
                        category.add("就職、資格");
                        break;
                    case "12851":
                        category.add("法律、社会");
                        break;
                    case "12996":
                        category.add("理学、工学");
                        break;
                    case "11430":
                        category.add("楽譜、音楽書");
                        break;
                    case "15713":
                        category.add("電子書籍");
                        break;
                    case "11493":
                        category.add("関連グッズ");
                        break;
                    }
                break;
            case "47727":
                category.add("レンタル、各種サービス");
                switch(this.categoryId){
                    case "47824":
                        category.add("お掃除、訪問サービス");
                        break;
                    case "47776":
                        category.add("レンタル");
                        break;
                    case "47889":
                        category.add("リフォーム");
                        break;
                    case "47811":
                        category.add("衣料品お直し");
                        break;
                    case "47817":
                        category.add("クリーニング");
                        break;
                    case "47898":
                        category.add("ペット関連サービス");
                        break;
                    case "47909":
                        category.add("サービスクーポン、引換券");
                        break;
                    case "48831":
                        category.add("車関連サービス");
                        break;
                    case "47905":
                        category.add("その他サービス");
                        break;
                    }
                break;
        }             
        return category;
    }
    public String getSortName(){
        switch(this.sort){
            case "-score":
                return "おすすめ順";
            case "+price":
                return "価格安い順";
            case "-price":
                return "価格高い順";
        }
        return "おすすめ順";
    }
}
