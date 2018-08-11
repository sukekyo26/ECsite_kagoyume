/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.Cookie;

/**
 * Yahoo!APIの商品検索をするクラス
 * @author sumi3
 */
public class SearchYahooApi {
    
    public static SearchYahooApi getInstance(){
        return new SearchYahooApi();
    }
    /**
     * YahooAPIでキーワード検索し、SearchResultで返す
     * @param sr
     * @return SearchResult
     * @throws Exception 
     */
    public SearchResult SearchItem(SearchResult sr) throws Exception{
        try{
             
            
            String url = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj00aiZpPVBnaFJES3VpSUZ4aiZzPWNvbnN1bWVyc2VjcmV0Jng9YmQ-&hits=50&image_size=300";
            //URLの文字列をセット
            if(!sr.getKeyword().equals("")){
                String query = URLEncoder.encode(sr.getKeyword(), "UTF-8");//日本語を検索する時用にURLエンコード
                url += "&query=" + query;
            }
            if(!sr.getCategoryId().equals("")){
                String categoryId = URLEncoder.encode(sr.getCategoryId(), "UTF-8");
                url += "&category_id=" + categoryId;
            }
            if(!sr.getSort().equals("")){
                String sort = URLEncoder.encode(sr.getSort(), "UTF-8");
                url += "&sort=" + sort;
            }
            if(!sr.getHighPrice().equals("")){
                //最高金額指定の入力があれば中を通るURLに文字列を追加する処理
                String price_to = sr.getHighPrice();
                url += "&price_to=" + price_to;
            }
            if(!sr.getLowPrice().equals("")){
                //最低金額指定の入力があれば中を通るURLに文字列を追加する処理
                String price_from = sr.getLowPrice();
                url += "&price_from=" + price_from;
            }
            
            URL setUrl = new URL(url);//StringからURLオブジェクトを生成
            HttpURLConnection con = (HttpURLConnection)setUrl.openConnection();
            //URL接続オブジェクトを生成する。URLクラスのopenConnectionインスタンス(URLが参照するリモートオブジェクトへの接続を表す(Http)URLConnectionインスタンスを返す)
            con.connect();//URLに接続
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            //クラスBufferedReader(文字型入力ストリームを作成)
            //クラスInputStreamReader(InputStreamReaderはバイト・ストリームから文字ストリームへの橋渡しの役目を持つ)
            //クラスURLConnectionのgetInputStreamメソッド(この接続からの入力を受け取る入力(バイト)ストリーム(InputStream型)を返します)
            
            String result = "";
            String tmp = "";
            while((tmp = in.readLine()) != null) {
                //BufferedReaderクラスのreadLineメソッド(テキスト行を読み込む)
                result += tmp;
            }
            
            in.close();//クラスBufferedReaderのcloseインスタンス(ストリームを閉じて、それに関連するすべてのシステム・リソースを解放する。)
            con.disconnect();//クラスHttpURLConnectionのdisconnectインスタンス(サーバーへの要求が近い将来発生しそうにないことを示す。)
            //out.print(result);
            ObjectMapper mapper = new ObjectMapper();
            //JSONの読み込み書き込みが出来るObjectMapperクラスのインスタンスを生成
            JsonNode jn = mapper.readTree(result);
            //ObjectMapperクラスのreadTreeメソッド(JsonNodeインスタンスのセットを使用して表現されたツリーとしてJSONコンテンツを逆シリアル化するメソッド)
            
            if(jn.get("ResultSet").get("totalResultsAvailable").asInt() != 0){
            //検索結果に該当するデータが無ければ処理は行わない
                int searchNumber = 0;
                if(jn.get("ResultSet").get("totalResultsAvailable").asInt() < 50){
                    searchNumber = jn.get("ResultSet").get("totalResultsAvailable").asInt();//検索結果に該当するデータが50件以下の場合
                    sr.setHitCount(searchNumber);//検索結果にヒットしたアイテム数をセット
                } else {
                    searchNumber = 50;//検索結果に該当するデータが50件以上の場合
                    sr.setHitCount(50);//50件のアイテム数をセット
                }
                
                for(int a = 0; a < searchNumber; a++){
                    //検索結果の上からデータをSearchResultクラスのインスタンスsrにセットしセッション(SearchResult)に格納
                    String name = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(a)).get("Name").asText();
                    String description = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(a)).get("Description").asText();
                    String thumnail = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(a)).get("Image").get("Medium").asText();
                    String largeThumnail = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(a)).get("ExImage").get("Url").asText();
                    int price = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(a)).get("Price").get("_value").asInt();
                    String rate = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(a)).get("Review").get("Rate").asText();
                    String code = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(a)).get("Code").asText();
                    sr.setName(name);
                    sr.setDescription(description);
                    sr.setThumnail(thumnail);
                    sr.setLargeThumnail(largeThumnail);
                    sr.setPrice(price);
                    sr.setRate(rate);
                    sr.setItemCode(code);
                }
            }
            return sr;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new Exception(e);
        }    
    }
    
    /**
     * ArrayListに格納されているキーワードを上から順番にYahooAPIで検索し、キーワードにつき１つのデータをSearchResultクラスに格納
     * @param keywords
     * @return SearchResult
     * @throws Exception 
     */
    public SearchResult SearchByMultiQuery(ArrayList<String> keywords) throws Exception{
        try{
            SearchResult sr = new SearchResult();
            int searchNumber = 0;
            for(int num = 0; num < 12; num++){ 
            
                String url = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj00aiZpPVBnaFJES3VpSUZ4aiZzPWNvbnN1bWVyc2VjcmV0Jng9YmQ-&hits=10&image_size=300";
                //URLの文字列をセット
                if(!keywords.get(num).equals("")){
                    String query = URLEncoder.encode(keywords.get(num), "UTF-8");//日本語を検索する時用にURLエンコード
                    url += "&query=" + query;
                }
            
                URL setUrl = new URL(url);//StringからURLオブジェクトを生成
                HttpURLConnection con = (HttpURLConnection)setUrl.openConnection();
                //URL接続オブジェクトを生成する。URLクラスのopenConnectionインスタンス(URLが参照するリモートオブジェクトへの接続を表す(Http)URLConnectionインスタンスを返す)
                con.connect();//URLに接続
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //クラスBufferedReader(文字型入力ストリームを作成)
                //クラスInputStreamReader(InputStreamReaderはバイト・ストリームから文字ストリームへの橋渡しの役目を持つ)
                //クラスURLConnectionのgetInputStreamメソッド(この接続からの入力を受け取る入力(バイト)ストリーム(InputStream型)を返します)
            
                String result = "";
                String tmp = "";
                while((tmp = in.readLine()) != null) {
                    //BufferedReaderクラスのreadLineメソッド(テキスト行を読み込む)
                    result += tmp;
                }
            
                in.close();//クラスBufferedReaderのcloseインスタンス(ストリームを閉じて、それに関連するすべてのシステム・リソースを解放する。)
                con.disconnect();//クラスHttpURLConnectionのdisconnectインスタンス(サーバーへの要求が近い将来発生しそうにないことを示す。)
                //out.print(result);
                ObjectMapper mapper = new ObjectMapper();
                //JSONの読み込み書き込みが出来るObjectMapperクラスのインスタンスを生成
                JsonNode jn = mapper.readTree(result);
                //ObjectMapperクラスのreadTreeメソッド(JsonNodeインスタンスのセットを使用して表現されたツリーとしてJSONコンテンツを逆シリアル化するメソッド)
            
                if(jn.get("ResultSet").get("totalResultsAvailable").asInt() != 0){
                    //検索結果に該当するデータが無ければ処理は行わない
                    Random rand = new Random();
                    int random = 0;
                    int sum = jn.get("ResultSet").get("totalResultsAvailable").asInt();//YahooApiの検索でヒットした数
                    if(sum < 10){//最大検索数(hits)が5なのでヒット数が5以下の場合5以上の場合で処理を分ける
                            random = rand.nextInt(sum);//該当したJsonデータの中からランダムに一つの値を取得
                        } else {
                            random = rand.nextInt(10);//該当したJsonデータの中からランダムに一つの値を取得
                        }
                    //検索結果の上からデータをSearchResultクラスのインスタンスsrにセットしセッション(SearchResult)に格納
                    String name = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(random)).get("Name").asText();
                    String description = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(random)).get("Description").asText();
                    String thumnail = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(random)).get("Image").get("Medium").asText();
                    String largeThumnail = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(random)).get("ExImage").get("Url").asText();
                    int price = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(random)).get("Price").get("_value").asInt();
                    String rate = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(random)).get("Review").get("Rate").asText();
                    String code = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(random)).get("Code").asText();
                    sr.setName(name);
                    sr.setDescription(description);
                    sr.setThumnail(thumnail);
                    sr.setLargeThumnail(largeThumnail);
                    sr.setPrice(price);
                    sr.setRate(rate);
                    sr.setItemCode(code);
                    searchNumber++;
                }
            }
            sr.setHitCount(searchNumber);
            return sr;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new Exception(e);
        }    
    }
    /**
     * ArrayList内の商品コード12件を検索(ArrayListに商品コードが格納されていれば検索可能)
     * @param itemCode
     * @return SearchResult
     * @throws Exception 
     */
    public SearchResult SearchByArrayList(ArrayList<String> itemCode) throws Exception{
        try{
            SearchResult sr = new SearchResult();
            int searchNumber = 0;
            for(int num = 0; num < itemCode.size(); num++){ 
            
                String url = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=dj00aiZpPVBnaFJES3VpSUZ4aiZzPWNvbnN1bWVyc2VjcmV0Jng9YmQ-&responsegroup=medium&image_size=300";
                //URLの文字列をセット
                
                String code = URLEncoder.encode(itemCode.get(num), "UTF-8");//日本語を検索する時用にURLエンコード
                url += "&itemcode=" + code;
                
            
                URL setUrl = new URL(url);//StringからURLオブジェクトを生成
                HttpURLConnection con = (HttpURLConnection)setUrl.openConnection();
                //URL接続オブジェクトを生成する。URLクラスのopenConnectionインスタンス(URLが参照するリモートオブジェクトへの接続を表す(Http)URLConnectionインスタンスを返す)
                con.connect();//URLに接続
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //クラスBufferedReader(文字型入力ストリームを作成)
                //クラスInputStreamReader(InputStreamReaderはバイト・ストリームから文字ストリームへの橋渡しの役目を持つ)
                //クラスURLConnectionのgetInputStreamメソッド(この接続からの入力を受け取る入力(バイト)ストリーム(InputStream型)を返します)
            
                String result = "";
                String tmp = "";
                while((tmp = in.readLine()) != null) {
                    //BufferedReaderクラスのreadLineメソッド(テキスト行を読み込む)
                    result += tmp;
                }
            
                in.close();//クラスBufferedReaderのcloseインスタンス(ストリームを閉じて、それに関連するすべてのシステム・リソースを解放する。)
                con.disconnect();//クラスHttpURLConnectionのdisconnectインスタンス(サーバーへの要求が近い将来発生しそうにないことを示す。)
                //out.print(result);
                ObjectMapper mapper = new ObjectMapper();
                //JSONの読み込み書き込みが出来るObjectMapperクラスのインスタンスを生成
                JsonNode jn = mapper.readTree(result);
                //ObjectMapperクラスのreadTreeメソッド(JsonNodeインスタンスのセットを使用して表現されたツリーとしてJSONコンテンツを逆シリアル化するメソッド)
                if(jn.get("ResultSet").get("totalResultsReturned").asInt() != 0){
                    //検索結果に該当するデータが無ければ処理は行わない
                    //検索結果の上からデータをSearchResultクラスのインスタンスsrにセットしセッション(SearchResult)に格納
                    String name = jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText();
                    String description = jn.get("ResultSet").get("0").get("Result").get("0").get("Description").asText();
                    String thumnail = jn.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Medium").asText();
                    String largeThumnail = jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText();
                    int price = jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt();
                    String rate = jn.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").asText();
                    sr.setName(name);
                    sr.setDescription(description);
                    sr.setThumnail(thumnail);
                    sr.setLargeThumnail(largeThumnail);
                    sr.setPrice(price);
                    sr.setRate(rate);
                    sr.setItemCode(code);
                    searchNumber++;
                }
            }
            sr.setHitCount(searchNumber);
            return sr;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        }    
    }
    
    /**
     * ユーザーが過去に購入した物をyahooAPIの商品コード検索で検索し、SearchBuyDataクラスに格納。
     * @param sbd
     * @return SearchBuyData
     * @throws Exception 
     */
    public SearchBuyData SearchBuyData(SearchBuyData sbd) throws Exception{
        try {
            for(int count = 0; count < sbd.getItemSize(); count++){        
                String itemCode = sbd.getItemCode(count);
                String url = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=dj00aiZpPVBnaFJES3VpSUZ4aiZzPWNvbnN1bWVyc2VjcmV0Jng9YmQ-&itemcode=" + itemCode;
                //URLの文字列をセット
            
                URL setUrl = new URL(url);//StringからURLオブジェクトを生成
                HttpURLConnection con = (HttpURLConnection)setUrl.openConnection();
                //URL接続オブジェクトを生成する。URLクラスのopenConnectionインスタンス(URLが参照するリモートオブジェクトへの接続を表す(Http)URLConnectionインスタンスを返す)
                con.connect();//URLに接続
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //クラスBufferedReader(文字型入力ストリームを作成)
                //クラスInputStreamReader(InputStreamReaderはバイト・ストリームから文字ストリームへの橋渡しの役目を持つ)
                //クラスURLConnectionのgetInputStreamメソッド(この接続からの入力を受け取る入力(バイト)ストリーム(InputStream型)を返します)
            
                String result = "";
                String tmp = "";
                while((tmp = in.readLine()) != null) {
                    //BufferedReaderクラスのreadLineメソッド(テキスト行を読み込む)
                    result += tmp;
                }
            
                in.close();//クラスBufferedReaderのcloseインスタンス(ストリームを閉じて、それに関連するすべてのシステム・リソースを解放する。)
                con.disconnect();//クラスHttpURLConnectionのdisconnectインスタンス(サーバーへの要求が近い将来発生しそうにないことを示す。)
                //out.print(result);
                ObjectMapper mapper = new ObjectMapper();
                //JSONの読み込み書き込みが出来るObjectMapperクラスのインスタンスを生成
                JsonNode jn = mapper.readTree(result);
                //ObjectMapperクラスのreadTreeメソッド(JsonNodeインスタンスのセットを使用して表現されたツリーとしてJSONコンテンツを逆シリアル化するメソッド)
                
                String name = jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText();
                String thumnail = jn.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small").asText();
                int price = jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt();
                sbd.setItemName(name);
                sbd.setThumnail(thumnail);
                sbd.setItemPrice(price);
                //商品名、サムネイル画像、価格
            }
            return sbd;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception(e);
        }    
    }
    /**
     * カート内の商品情報を検索
     * @param cdd
     * @return CartItem
     * @throws Exception 
     */
    public CartItem SearchCartItem(CartDataDTO cdd) throws Exception{
        try{
            CartItem ci = new CartItem();
            for(int count = 0; count < cdd.getItemSize(); count++){
                String itemCode =cdd.getItemCode(count);
                String url = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=dj00aiZpPVBnaFJES3VpSUZ4aiZzPWNvbnN1bWVyc2VjcmV0Jng9YmQ-&responsegroup=medium&image_size=300&itemcode=" + itemCode;
                
                URL setUrl= new URL(url);
                HttpURLConnection con = (HttpURLConnection)setUrl.openConnection();
                con.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
                String result = "";
                String tmp = "";
                while((tmp = in.readLine()) != null){
                    result += tmp;
                }
                
                in.close();
                con.disconnect();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jn = mapper.readTree(result);
                
                String name = jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText();
                String description = jn.get("ResultSet").get("0").get("Result").get("0").get("Description").asText();
                String thumnail = jn.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Medium").asText();
                String largeThumnail = jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText();
                int price = jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt();
                String rate = jn.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").asText();
                ci.setUserID(cdd.getUserID());
                ci.setName(name);
                ci.setThumnail(thumnail);
                ci.setLargeThumnail(largeThumnail);
                ci.setDescription(description);
                ci.setPrice(price);
                ci.setRate(rate);
                ci.setItemNumber(cdd.getItemNumber(count));
                ci.setItemCode(cdd.getItemCode(count));
                ci.setCartID(cdd.getCartID(count));
            }
            return ci;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        }  
    }
    /**
     * 検索ワードランキング1位から10位のキーワードをArrayListStringで返す
     * @param sr
     * @return ArrayListString
     * @throws Exception 
     */
    public ArrayList<String> getPopularKeyword() throws Exception{
        try{
            String url = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/queryRanking?appid=dj00aiZpPVBnaFJES3VpSUZ4aiZzPWNvbnN1bWVyc2VjcmV0Jng9YmQ-&hits=12";
            
            URL setUrl= new URL(url);
            HttpURLConnection con = (HttpURLConnection)setUrl.openConnection();
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
             
            String result = "";
            String tmp = "";
            while((tmp = in.readLine()) != null){
                result += tmp;
            }
                
            in.close();
            con.disconnect();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jn = mapper.readTree(result);
            
            ArrayList<String> keywords = new ArrayList<String>();
            for(int number = 0; number < 12; number++){
                String keyword = jn.get("ResultSet").get("0").get("Result").get(String.valueOf(number)).get("Query").asText();
                keywords.add(keyword);
            }
            
            return keywords;    
                    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        }  
    } 
}
