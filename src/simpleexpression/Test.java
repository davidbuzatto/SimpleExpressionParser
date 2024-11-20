package simpleexpression;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Test {
    
    public static void main( String[] args ) {
        
        String[] expressions = {
            "1 + 2",
            "1 - 4",
            "1 + 2 - 3",
            "1 - 2 + 3",
            "2 * 2 - 3",
            "2 - 2 * 3",
            "( 4 - 2 ) * 3",
            "2 / 2 * 3",
            "3 * 3 + 4 * 4",
        };
        
        System.out.println( "Without AST:\n" );
        for ( String expr : expressions ) {
            System.out.printf( "%s = %.2f\n", expr, ExpressionParser.eval( expr ) );
        }
        
        System.out.println( "\n\nWith AST:\n" );
        for ( String expr : expressions ) {
            System.out.printf( "%s = %.2f\n", expr, ExpressionParserAST.eval( expr ) );
            System.out.println( ExpressionParserAST.toString( expr ) );
            System.out.println();
        }
        
    }
    
}
