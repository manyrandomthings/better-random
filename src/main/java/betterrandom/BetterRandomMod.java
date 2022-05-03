package betterrandom;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;
import java.util.random.RandomGeneratorFactory;

public class BetterRandomMod implements PreLaunchEntrypoint {
	public static final String MOD_ID = "betterrandom";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".properties");
	public static final String DEFAULT_GENERATOR = "Xoroshiro128PlusPlus";

	public static final String GENERATOR_TYPE_CONFIG_KEY = "generatorType";

	// avilable generator types: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/package-summary.html#algorithms
	private static String GENERATOR_TYPE;

	@Override
	public void onPreLaunch() {
		loadConfig();
	}

	public static void loadConfig() {
		if(CONFIG_FILE.toFile().exists()) {
			try {
				// FileInputStream fileInputStream2 = new FileInputStream(CONFIG_FILE);
				InputStream fileInputStream = Files.newInputStream(CONFIG_FILE);

				Properties configProperties = new Properties();
				configProperties.load(fileInputStream);

				String generatorType = configProperties.getProperty(GENERATOR_TYPE_CONFIG_KEY);

				if(generatorType != null && !generatorType.isEmpty()) {
					boolean exists = RandomGeneratorFactory.all().anyMatch(f -> f.name().equals(generatorType));

					if(exists) {
						setGeneratorType(generatorType);

						// don't set default
						return;
					}
					else {
						LOGGER.warn("Unknown random generator type {}", generatorType);
					}
				}
			}
			catch (IOException e) {
				LOGGER.error("Couldn't read config file", e);
			}
		}

		// create default config file
		createDefaultConfig();

		// set default
		setGeneratorType(DEFAULT_GENERATOR);
	}

	private static void createDefaultConfig() {
		LOGGER.info("Creating default config");
		Properties properties = new Properties();
		properties.setProperty(GENERATOR_TYPE_CONFIG_KEY, DEFAULT_GENERATOR);

		try {
			OutputStream outputStream = Files.newOutputStream(CONFIG_FILE);
			properties.store(outputStream, "Better Random config. Available generator types: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/package-summary.html#algorithms");
		}
		catch (IOException e) {
			LOGGER.error("Couldn't write config file", e);
		}
	}

	private static void setGeneratorType(String type) {
		LOGGER.info("Setting BetterRandom generator type to {}", type);
		GENERATOR_TYPE = type;
	}

	public static String getGeneratorType() {
		Objects.requireNonNull(GENERATOR_TYPE, "Generator type accessed too early");
		return GENERATOR_TYPE;
	}
}
