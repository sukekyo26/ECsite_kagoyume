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
    public SearchResult SearchByQuery(SearchResult sr) throws Exception{
        try{
            String query = URLEncoder.encode(sr.getKeyword(), "UTF-8"); //日本語を検索する時用にURLエンコード
            String categoryId = URLEncoder.encode(sr.getCategoryId(), "UTF-8");
            String sort = URLEncoder.encode(sr.getSort(), "UTF-8");
            String url = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj00aiZpPVBnaFJES3VpSUZ4aiZzPWNvbnN1bWVyc2VjcmV0Jng9YmQ-&hits=10&image_size=300&query=" + query + "&category_id=" + categoryId + "&sort=" + sort;
            //URLの文字列をセット
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
                if(jn.get("ResultSet").get("totalResultsAvailable").asInt() < 10){
                    searchNumber = jn.get("ResultSet").get("totalResultsAvailable").asInt();//検索結果に該当するデータが10件以下の場合
                    sr.setHitCount(searchNumber);//検索結果にヒットしたアイテム数をセット
                } else {
                    searchNumber = 10;//検索結果に該当するデータが10件以上の場合
                    sr.setHitCount(10);//10件のアイテム数をセット
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
     * ユーザーが過去に購入した物をyahooAPIの商品コード検索で検索し、SearchBuyDataクラスに格納。
     * @param sbd
     * @return
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
}
