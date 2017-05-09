package com.hribol.spiderman.cli;

import com.hribol.spiderman.cli.handlers.CommandHandler;
import com.hribol.spiderman.cli.handlers.InitCommandHandler;
import com.hribol.spiderman.cli.handlers.RecordCommandHandler;
import com.hribol.spiderman.cli.handlers.ReplayCommandHandler;
import org.apache.commons.io.IOUtils;
import org.docopt.Docopt;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by hvrigazov on 14.03.17.
 */
public class Main {

    static class Commands {
        static final String INIT = "init";
        static final String RECORD = "record";
        static final String REPLAY = "replay";
    }


    public static void main(String[] args) {
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/cli-specification.txt");
            String doc = IOUtils.toString(inputStream);
            Docopt docopt = new Docopt(doc);
            Map<String, Object> opts = docopt.withVersion("Spiderman 0.1").parse(args);

            System.out.println(opts);

            Map<String, CommandHandler> commandToHandler = getCommands();
            Optional<String> optionalSelectedCommand = commandToHandler
                    .keySet()
                    .stream()
                    .filter(command -> opts.get(command)
                    .equals(true))
                    .findAny();

            optionalSelectedCommand.ifPresent(command -> commandToHandler.get(command).handle(opts));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    private static Map<String, CommandHandler> getCommands() {
        Map<String, CommandHandler> map = new HashMap<>();
        map.put(Commands.INIT, new InitCommandHandler());
        map.put(Commands.RECORD, new RecordCommandHandler());
        map.put(Commands.REPLAY, new ReplayCommandHandler());
        return map;
    }
}
