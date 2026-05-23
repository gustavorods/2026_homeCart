package gustavorods.homecart.service;

import gustavorods.homecart.dto.ResidenceRequestDTO;
import gustavorods.homecart.infra.exceptions.UserNotFoundException;
import gustavorods.homecart.model.ResidenceMemberModel;
import gustavorods.homecart.model.ResidenceModel;
import gustavorods.homecart.model.UsersModel;
import gustavorods.homecart.repository.ResidenceMemberRepository;
import gustavorods.homecart.repository.ResidenceRepository;
import gustavorods.homecart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ResidenceMemberRepository  residenceMemberRepository;


    public int create(ResidenceRequestDTO dto) {

        Optional<UsersModel> owner = usersRepository.findByEmail(dto.ownerEmail());

        if (owner.isEmpty()) {
            throw new UserNotFoundException(dto.ownerEmail());
        }

        ResidenceModel residence = new ResidenceModel();

        int inviteCode = generateInviteCode();

        residence.setName(dto.name());
        residence.setOwner(owner.get());
        residence.setInviteCode(inviteCode);
        residenceRepository.save(residence);

        ResidenceMemberModel residenceMember = new ResidenceMemberModel();

        residenceMember.setUser(owner.get());
        residenceMember.setRole("owner");
        residenceMember.setResidence(residence);
        residenceMemberRepository.save(residenceMember);

        return inviteCode;
    }

    private int generateInviteCode() {
        SecureRandom random = new SecureRandom();

        int code;

        do {
            code = 100000 + random.nextInt(900000);

        } while (residenceRepository.existsByInviteCode(code));

        return code;
    }
}