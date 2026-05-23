package gustavorods.homecart.service;

import gustavorods.homecart.dto.AddMemberRequestDTO;
import gustavorods.homecart.dto.ResidenceRequestDTO;
import gustavorods.homecart.infra.exceptions.ResidenceNotFoundException;
import gustavorods.homecart.infra.exceptions.UserNotFoundException;
import gustavorods.homecart.model.ResidenceMemberModel;
import gustavorods.homecart.model.ResidenceModel;
import gustavorods.homecart.model.UsersModel;
import gustavorods.homecart.repository.ResidenceMemberRepository;
import gustavorods.homecart.repository.ResidenceRepository;
import gustavorods.homecart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddResidenceMemberService {
    @Autowired
    private ResidenceRepository residenceRepository;

    @Autowired
    private ResidenceMemberRepository residenceMemberRepository;

    @Autowired
    private UsersRepository usersRepository;

    public boolean addResidenceMember(AddMemberRequestDTO dto) {
        //  Trying to find a residence based on the code.
        Optional<ResidenceModel> residence =  residenceRepository.findByInviteCode(dto.residenceCode());

        if (residence.isEmpty()) {
            throw new ResidenceNotFoundException(dto.residenceCode());
        }

        //  Trying to find a user based on the email.
        Optional<UsersModel> user = usersRepository.findByEmail(dto.email());

        if (user.isEmpty()) {
            throw new UserNotFoundException(dto.email());
        }

        ResidenceMemberModel  residenceMemberModel = new ResidenceMemberModel();

        residenceMemberModel.setResidence(residence.get());
        residenceMemberModel.setUser(user.get());
        residenceMemberModel.setRole(dto.role());

        residenceMemberRepository.save(residenceMemberModel);

        return true;
    }
}
