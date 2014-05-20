/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool;

import java.util.HashMap;

/**
 *
 * @author jonimane
 */
public class SessionManager extends HashMap<String, Object>{
    private static SessionManager sm;
    
    private SessionManager()
    {
        
    }
    
    public static SessionManager getInstance()
    {
        if( sm == null )
        {
            sm = new SessionManager();
        }
        
        return sm;
    }
}
