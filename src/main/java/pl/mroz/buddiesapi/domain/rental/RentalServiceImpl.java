package pl.mroz.buddiesapi.domain.rental;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository repository;

    @Override
    public int doSomething() {
        log.info("hello there");
        return repository.someMethod();

    }
}
