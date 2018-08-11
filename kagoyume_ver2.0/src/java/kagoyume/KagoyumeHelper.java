/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

/**
 *
 * @author sumi3
 */
public class KagoyumeHelper {
    private final String topURL = "Top";
    
    
    public static KagoyumeHelper getInstance(){
        return new KagoyumeHelper();
    }
    
    public String home(){
        return "<a href=\""+topURL+"\">トップへ戻る</a>";
    }
    
}
