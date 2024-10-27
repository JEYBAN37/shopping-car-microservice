package com.example.car.transactiontest;

import com.example.car.domain.model.entity.Car;
import com.example.car.domain.model.exception.CarException;
import com.example.car.domain.port.dao.CarDao;
import com.example.car.domain.port.repository.CarRepository;
import com.example.car.domain.service.CarCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarCreateServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarCreateService carCreateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteWithNonExistingUser() {
        Long userId = 1L;

        // Simular que el usuario no existe
        when(carDao.idExist(userId)).thenReturn(false);

        Car car = new Car(); // Asumiendo que el constructor está definido.
        when(carRepository.create(any(Car.class))).thenReturn(car);

        Car createdCar = carCreateService.execute(userId);

        // Verifica que el método de creación se llame una vez
        verify(carDao, times(1)).idExist(userId);
        verify(carRepository, times(1)).create(any(Car.class));
        // Puedes agregar más aserciones según la lógica de tu aplicación
    }

    @Test
    void testExecuteWithExistingUser() {
        Long userId = 1L;

        // Simular que el usuario ya existe
        when(carDao.idExist(userId)).thenReturn(true);

        assertThrows(CarException.class, () -> carCreateService.execute(userId));

        // Verifica que el método de verificación de existencia se llame una vez
        verify(carDao, times(1)).idExist(userId);
        verify(carRepository, never()).create(any(Car.class)); // No debe llamar a create
    }
}
