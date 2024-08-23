package buddiesapi.domain.account

object AccountDomainServiceFactory {
    @JvmStatic
    fun accountDomainService(repository: AccountRepository): AccountDomainService {
        return AccountDomainServiceImpl(repository)
    }
}
