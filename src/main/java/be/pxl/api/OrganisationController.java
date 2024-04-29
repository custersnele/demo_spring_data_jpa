package be.pxl.api;

import be.pxl.api.data.JoinOrganisationDto;
import be.pxl.service.OrganisationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {

    private final OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @PostMapping("/join")
    public void joinOrganisation(@RequestBody JoinOrganisationDto joinOrganisationDto) {
        organisationService.joinOrganisation(joinOrganisationDto.organisation(), joinOrganisationDto.employee());
    }

}
