package com.example.transaction.domain.service;
import com.example.transaction.domain.model.dto.command.CarCreateCommand;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.domain.model.exception.CarException;
import com.example.transaction.domain.port.dao.CarDao;
import com.example.transaction.domain.port.repository.CarRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.example.transaction.domain.model.constant.CarConstant.LIST_EMPTY;
import static com.example.transaction.domain.model.constant.CarConstant.MESSAGE_ERROR_ADD;

@AllArgsConstructor
public class CarCreateService {
    private final CarRepository carRepository;
    private final CarDao carDao;



    public List<Car> execute(List<CarCreateCommand> createCommands) {
        if (createCommands.isEmpty())
           throw new CarException(LIST_EMPTY);
        return createCommands.stream()
                .map(this::processCreateCommand)
                .toList();
    }

    private Car processCreateCommand(CarCreateCommand createCommand) {
        validateParams(createCommand);
        Car carToCreate = createSupply(createCommand);
        return carRepository.create(carToCreate);
    }

    private Car createSupply(CarCreateCommand createCommand) {
        return new Car().requestToCreate(createCommand);
    }

    private void validateParams(CarCreateCommand createCommand) {
        if (createCommand.getId() != null && carDao.idExist(createCommand.getId()))
            throw new CarException(MESSAGE_ERROR_ADD);
    }
}