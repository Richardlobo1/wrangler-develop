package io.cdap.wrangler.parser;

import io.cdap.wrangler.api.parser.Token;
import io.cdap.wrangler.api.parser.TokenGroup;
import io.cdap.wrangler.api.parser.TokenType;

public class WranglerVisitorImpl extends WranglerBaseVisitor<Token> {

    @Override
    public Token visitByteSizeArg(WranglerParser.ByteSizeArgContext ctx) {
        String value = ctx.getText(); // e.g., "10MB"
        Token token = new Token(TokenType.BYTE_SIZE, value);
        TokenGroup.addToken(token);
        return token;
    }

    @Override
    public Token visitTimeDurationArg(WranglerParser.TimeDurationArgContext ctx) {
        String value = ctx.getText(); // e.g., "5s"
        Token token = new Token(TokenType.TIME_DURATION, value);
        TokenGroup.addToken(token);
        return token;
    }

    @Override
    public Token visitValue(WranglerParser.ValueContext ctx) {
        if (ctx.byteSizeArg() != null) {
            return visitByteSizeArg(ctx.byteSizeArg());
        } else if (ctx.timeDurationArg() != null) {
            return visitTimeDurationArg(ctx.timeDurationArg());
        }
        return super.visitValue(ctx); // default case
    }
}
