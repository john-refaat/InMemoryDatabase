package demo.memory.io;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for printing output messages. It prints on the console.
 * Can be enhanced to output to different PrintStreams
 * 
 * @author john
 *
 */
@Component
@PropertySources({ @PropertySource(value = "messages.properties", ignoreResourceNotFound = true) })
public class OutputManager {

	@Autowired
	Environment env;
	
	public void write(String output) {
		System.out.println(output);
	}
	
	public void writeMessage(String key, Object[] args) {
		MessageFormat messageFormat = new MessageFormat(env.getProperty(key));
		write(messageFormat.format(args));
	}
	
	public void writeMessage(String key) {
		write(env.getProperty(key));
	}
}
