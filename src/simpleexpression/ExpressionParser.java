package simpleexpression;

import java.util.List;

/**
 * LL(1) Parser for arithmetic expressions.
 * 
 * It does not check for expression validity so, it assumes that the
 * expression is syntactically correct.
 * 
 * Grammar (EBNF):
 * 
 *     expression -> term ( addingOp term )* .
 *     addingOp -> "+" | "-" .
 * 
 *     term -> factor ( multiplyingOp factor )* . 
 *     multiplyingOp -> "*" | "/" .
 * 
 *     factor -> constValue | "(" expression ")" .
 *     constValue -> [0..1]+ .
 * 
 * @author Prof. Dr. David Buzatto
 */
public class ExpressionParser {
    
    private List<Token> tokens;
    private int pos;
    private double result;
    
    public static double eval( String expression ) {
        return new ExpressionParser( expression ).getResult();
    }
    
    private ExpressionParser( String expression ) {
        this.tokens = ExpressionLexer.getTokens( expression );
        result = parseExpression();
    }
    
    private Token nextToken() {
        if ( pos < tokens.size() ) {
            return tokens.get( pos++ );
        }
        return null;
    }
    
    private Token currentToken() {
        if ( pos < tokens.size() ) {
            return tokens.get( pos );
        }
        return null;
    }
    
    // expression -> term ( addingOp term )* .
    // addingOp -> "+" | "-" .
    private double parseExpression() {
        
        double term = 0;
        double term2 = 0;
        Token operator = null;
        
        term = parseTerm();
        
        while ( currentToken() != null && (
                currentToken().type == Token.Type.PLUS ||
                currentToken().type == Token.Type.MINUS ) ) {
            operator = nextToken();
            term2 = parseTerm();
            if ( operator.type == Token.Type.PLUS ) {
                term = term + term2;
            } else {
                term = term - term2;
            }
        }
        
        return term;
        
    }
    
    // term -> factor ( multiplyingOp factor )* . 
    // multiplyingOp -> "*" | "/" .
    private double parseTerm() {
        
        double factor = 0;
        double factor2 = 0;
        Token operator = null;
        
        factor = parseFactor();
        
        while ( currentToken() != null && (
                currentToken().type == Token.Type.TIMES ||
                currentToken().type == Token.Type.DIVIDE ) ) {
            operator = nextToken();
            factor2 = parseFactor();
            if ( operator.type == Token.Type.TIMES ) {
                factor = factor * factor2;
            } else {
                factor = factor / factor2;
            }
        }
        
        return factor;
        
    }
    
    // factor -> constValue | "(" expression ")" .
    // constValue -> [0..1]+ .
    private double parseFactor() {
        
        double value = 0;
        
        Token t = nextToken();
        
        if ( t.type == Token.Type.NUMBER ) {
            value = Double.parseDouble( t.value );
        } else if ( t.type == Token.Type.LEFT_PAREN ) {
            value = parseExpression();
            nextToken();
        }
        
        return value;
        
    }
    
    public double getResult() {
        return result;
    }
    
}
