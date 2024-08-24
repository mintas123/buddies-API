package buddiesapi.common

import buddiesapi.domain.AccountBuilder
import buddiesapi.domain.common.ObjectMapper
import spock.lang.Specification
import spock.lang.Subject

class ObjectMapperUT extends Specification {

    @Subject
    private final ObjectMapper objectMapper = ObjectMapper.INSTANCE

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
            def result = objectMapper.mapInto(newAcc, old)
        then:
            result.accountId == uuid
            result.email == 'test2@mail.com'
            result.lastName == newAcc.lastName
    }
}
