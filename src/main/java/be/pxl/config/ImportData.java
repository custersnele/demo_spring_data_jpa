package be.pxl.config;

import be.pxl.domain.Organisation;
import be.pxl.repository.OrganizationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ImportData implements CommandLineRunner {

	private final Logger LOGGER = LogManager.getLogger(ImportData.class);


	private final OrganizationRepository organizationRepository;

	public ImportData(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	@Override
	public void run(String... args) {
		LOGGER.info("Importing testdata...");


		organizationRepository.save(new Organisation("Happy Organisation"));

	}
}

