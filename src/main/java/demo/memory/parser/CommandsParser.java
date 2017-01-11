package demo.memory.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import demo.memory.DataBaseLexer;
import demo.memory.DataBaseParser;
import demo.memory.exceptions.DatabaseRuntimeException;


/**
 * This parser uses ANTLR to parse the commands according to the grammar defined
 * in the file DataBase.g4<br/>
 * 
 * ANTLR generates a Lexer and a Parser to parse the commands and provide a
 * Parse Tree. Then the class walks<br/>
 * the tree returns an array containing alphanumeric tokens since these are the
 * tokens that represent the command<br/>
 * name and the parameters
 * 
 * @author john
 *
 */
public class CommandsParser {

	public String[] parse(String command) {
		List<String> commandList = new ArrayList<>();

		try {
			CharStream inputCharStream = new ANTLRInputStream(new StringReader(command));
			TokenSource tokenSource = new DataBaseLexer(inputCharStream);
			TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
			DataBaseParser parser = new DataBaseParser(inputTokenStream);
			ParseTree tree = parser.statement();

			if (parser.getNumberOfSyntaxErrors() == 0) {
				commandList = walkTree(tree);
			}
		} catch (IOException e) {
			throw new DatabaseRuntimeException(e);
		}
		return commandList.toArray(new String[commandList.size()]);
	}

	private List<String> walkTree(ParseTree tree) {
		List<String> cmdlst = new ArrayList<>();
		String[] arr = tree.getText().split(" ");
		String cmd = arr[0];
		cmdlst.add(cmd);
		if (arr.length > 1) {
			String paramString = tree.getText().substring(cmd.length());

			String[] param = paramString.split("-");
			cmdlst.addAll(Arrays.asList(param).stream().map(s->s.trim()).collect(Collectors.toList()));
		}
		return cmdlst;
	}

}
