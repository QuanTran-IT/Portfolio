package org.quan.portfolio.infomation.service.impl;

import org.quan.portfolio.infomation.dto.ContactForm;
import org.quan.portfolio.infomation.entity.ContactMessage;
import org.quan.portfolio.infomation.repository.ContactMessageRepository;
import org.quan.portfolio.infomation.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactMessageRepository contactMessageRepository;

    public ContactServiceImpl(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public void save(ContactForm contactForm) {
    contactMessageRepository.save(new ContactMessage(contactForm.getEmail(), contactForm.getMessage()));
    }
}
