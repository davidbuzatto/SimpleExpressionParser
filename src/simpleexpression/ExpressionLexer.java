package simpleexpression;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple lexer.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class ExpressionLexer {
    
    // all tokens must be separated by at least one space
    public static List<Token> getTokens( String expression ) {
        
        List<Token> tokens = new ArrayList<>();
        
        for ( String s : expression.split( "\\s+" ) ) {
            
            Token t = new Token();
            t.value = s;
            
            switch ( s ) {
                case "+": 
                    t.type = Token.Type.PLUS;
                    break;
                case "-": 
                    t.type = Token.Type.MINUS;
                    break;
                case "*": 
                    t.type = Token.Type.TIMES;
                    break;
                case "/": 
                    t.type = Token.Type.DIVIDE;
                    break;
                case "(": 
                    t.type = Token.Type.LEFT_PAREN;
                    break;
                case ")": 
                    t.type = Token.Type.RIGHT_PAREN;
                    break;
                default:
                    t.type = Token.Type.NUMBER;
                    t.value = s;
                    break;
            }
            
            tokens.add( t );
            
        }
        
        return tokens;
        
    }
    
}
