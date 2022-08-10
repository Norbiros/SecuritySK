package dev.norbiros.securitysk.skript.effects;

import dev.norbiros.securitysk.utils.LogFilter;

import org.bukkit.event.Event;

import java.lang.NullPointerException;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffHideLogs extends Effect {

    private Expression<String> command;
    private LogFilter filter = new LogFilter();
    private boolean hideOrShow;

    static {
        Skript.registerEffect( EffHideLogs.class, "(hide|show) [console ]logs for [(string|text|command) ]%string%" );
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.command = (Expression<String>) expressions[0];
        hideOrShow = parser.expr.contains("hide");
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return (hideOrShow ? "hide" : "show") + "logs for string " + command.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (hideOrShow == true) { filter.addCommand(command.getSingle(event));
        } else { filter.removeCommand(command.getSingle(event)); }
    }
}