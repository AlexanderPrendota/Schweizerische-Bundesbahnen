package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.ScheduleRepository;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.StationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.schweizerischebundesbahnen.restcontroller.ScheduleController.CURRENT_DAY;

/**
 * Created by aleksandrprendota on 29.03.17.
 *
 * Implimentation ScheduleService interface
 */

@Service
@Log4j
public class ScheduleServiceImp implements ScheduleService {

    private static final Date TODAYS_DATE = Calendar.getInstance().getTime();
    private static final long ONE_DAY = 3600L * 24L * 1000L;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StationService stationService;
    /**
     * Creating a new schedule entity in database
     * @param schedule new schedule entity
     */
    @Override
    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    /**
     * Delete schedule entity from database
     * @param schedule existing scedule entity
     */
    @Override
    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    /**
     * Findin schedule entity by id
     * @param id of schedule entity
     * @return existing schedule entity
     */
    @Override
    public Schedule findScheduleById(Long id) {
        return scheduleRepository.findOne(id);
    }


    /**
     * Finding rides user wants to see
     * Getting List of Schedule by two station in the period of time
     * @param stationDeparture station entity
     * @param stationArrival station entity
     * @param start start date
     * @param end end date
     * @return List of schedule in the period of time from database
     */
    @Override
    public List<Schedule> findUserRides(Station stationDeparture, Station stationArrival, Date start, Date end) {
        return scheduleRepository.findByStationDepartureAndStationArrivalAndTimeDepartureBetween(
                stationDeparture,
                stationArrival,
                start,
                end
        );
    }

    /**
     * Find List of Schedules by Station Departure field
     * @param station station entity
     * @return List of schedule on current station in param
     */
    @Override
    public List<Schedule> findByStationDeparture(Station station) {
        return scheduleRepository.findByStationDeparture(station);
    }

    /**
     * get list of all schedule
     * @return list of all schedule entity
     */
    @Override
    public List<Schedule> findAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        scheduleRepository.findAll().forEach(schedules::add);
        return schedules;
    }

    /**
     * get list of schedule by specific train
     * @param train
     * @return list of schedule entity
     */
    @Override
    public List<Schedule> findByTrain(Train train) {
        return scheduleRepository.findByTrain(train);
    }

    /**
     * get list of schedule by specific station
     * @param station
     * @return list of schedule entity
     */
    @Override
    public List<Schedule> findByStationArrival(Station station) {
        return scheduleRepository.findByStationArrival(station);
    }

    /**
     * get list of schedule by user wish: station departure and time > current time
     * @param station
     * @return  list of schedule entity
     */
    @Override
    public List<Schedule> findByUserStation(Station station) {
        return scheduleRepository.findByStationDepartureAndTimeDepartureGreaterThanEqual(station,TODAYS_DATE);

    }

    /**
     * get list of schedule by time departure
     * @param date
     * @return list of schedule entity
     */
    @Override
    public List<Schedule> findByTimeDeparture(Date date) {
        return scheduleRepository.findByTimeDeparture(date);
    }

    /**
     *  get list of schedule with transfer ways
     * @param departure
     * @param arrival
     * @param dateDeparture
     * @return list of schedule entity
     */
    @Override
    public List<Schedule> findTransferSchedule(Station departure, Station arrival, Date dateDeparture) {

        Date endDate = new Date(dateDeparture.getTime() + CURRENT_DAY);
        List<Schedule> resultTransferSchedule = scheduleRepository
                .findByStationDepartureAndStationArrivalAndTimeDepartureBetween(departure,arrival,dateDeparture,endDate);

        List<Schedule> possibleScheduleByStationDeparture = scheduleRepository
                .findByStationDepartureAndTimeDepartureBetween(departure, dateDeparture, endDate);

        if (possibleScheduleByStationDeparture.size() == 0){
            return resultTransferSchedule;
        }

        for (Schedule possibleSchedule : possibleScheduleByStationDeparture) {
            Date currentTransferDay = new Date(possibleSchedule.getTimeArrival().getTime() + ONE_DAY);
            List<Schedule> transferSchedule = scheduleRepository
                    .findByStationDepartureAndStationArrivalAndTimeDepartureBetween(
                            possibleSchedule.getStationArrival(),
                            arrival,
                            possibleSchedule.getTimeArrival(), currentTransferDay
                    );
            if (transferSchedule.size() != 0){
                resultTransferSchedule.add(possibleSchedule);
                for (Schedule schedule : transferSchedule) {
                    resultTransferSchedule.add(schedule);
                }
            }
        }
        return resultTransferSchedule;
    }

}
