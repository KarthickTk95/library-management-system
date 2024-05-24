package com.example.library_management.Service;

import com.example.library_management.Model.Members;
import com.example.library_management.Repository.MemberRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Members> getAllMembers() {
        // Implement logic to get all members from repository
        return memberRepository.findAll();
    }

    public Members getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Member not found with id: " + id, 1));
    }

    public Members createMember(Members member) {
        // Implement logic to create member in repository
        return memberRepository.save(member);
    }

    public Members updateMember(Long id, Members updatedMember) {
        // Implement logic to update member in repository
        Members existingMember = getMemberById(id);
        existingMember.setName(updatedMember.getName());
        existingMember.setPhone(updatedMember.getPhone());
        existingMember.setRegisteredDate(updatedMember.getRegisteredDate());
        return memberRepository.save(existingMember);
    }

    public void deleteMember(Long id) {
        // Implement logic to delete member from repository
        memberRepository.deleteById(id);
    }
}
