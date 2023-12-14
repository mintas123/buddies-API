package pl.mroz.buddiesapi.common

import pl.mroz.buddiesapi.domain.AccountBuilder
import pl.mroz.buddiesapi.domain.common.ObjectMapper
import spock.lang.Specification

class ObjectMapperUT extends Specification {

    def 'should map Account objects'() {
        given:
            def uuid = UUID.randomUUID()
            def old = new AccountBuilder()
                    .withAccountId(uuid)
                    .withEmail('test@mail.com')
                    .build()
            def newAcc = new AccountBuilder()
                    .withAccountId(uuid)
                    .withEmail('test2@mail.com')
                    .build()
        when:
            def result = ObjectMapper.mapInto(newAcc, old)
        then:
            result.accountId == uuid
            result.email == 'test2@mail.com'
            result.lastName == newAcc.lastName
    }
}
