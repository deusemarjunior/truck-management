package com.truck.deusemar.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.truck.deusemar.domain.Transport;
import com.truck.deusemar.domain.enums.PeriodEnum;
import com.truck.deusemar.domain.enums.StatusEnum;
import com.truck.deusemar.repository.TransportRepository;

@Service
public class TransportService {

    private final static ZoneId TZ = ZoneId.of("America/Sao_Paulo");
    private final Locale locale = Locale.ENGLISH;

    @Autowired
    TransportRepository trasportRepository;
	
	 @Autowired 
	 MongoTemplate mongoTemplate;

	  public List<Transport> findTransportActive() {
		  Query query = new Query();
		  query.addCriteria(Criteria.where("status").is(StatusEnum.ENABLED));
		 List<Transport> transports = mongoTemplate.find(query, Transport.class);
	    return  transports ;
	  }
	  
	  public Integer sumTransportActive(String idOrigin) {
		  Query query = new Query();
		  query.addCriteria(Criteria.where("origin.id").is(idOrigin));
		  query.addCriteria(Criteria.where("status").is(StatusEnum.ENABLED));
		  query.addCriteria(Criteria.where("truckHasLoad").is(false));
		  List<Transport> transports = mongoTemplate.find(query, Transport.class);
		 
	    return  transports == null ? 0 : transports.size();
	  }
	  
	  public  List<?>  getTransportGruped() {

		  //TODO verify if query  return -> List transport (Driver, Place origin, Place destination) Grouped by truck type 
		  
		  List<AggregationOperation> list = new ArrayList<AggregationOperation>();
	    	list.add(Aggregation.match(Criteria.where("status").is(StatusEnum.ENABLED)));
	    	list.add(Aggregation.sort(Sort.Direction.ASC, "drive.truckType"));
	    	
	    	TypedAggregation<Transport> agg = Aggregation.newAggregation(Transport.class, list);
	        return mongoTemplate.aggregate(agg, Transport.class, Transport.class).getMappedResults();

	  }
	  
	  public Integer getCurrentActiveLoadedByPeriod(String idOrigin, PeriodEnum period) {
		  Query query = new Query();
		  query.addCriteria(Criteria.where("origin.id").is(idOrigin));
		  query.addCriteria(Criteria.where("status").is(StatusEnum.ENABLED));
		  query.addCriteria(Criteria.where("truckHasLoad").is(true));
		  
		  LocalDate startOfDay = LocalDate.now();
		  LocalDate endOfDay = LocalDate.now();

		  switch (period) {
			case DAY:
			      query.addCriteria(Criteria.where("date").is(startOfDay));
				break;
			case WEEK:
				 DayOfWeek firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
			     DayOfWeek lastDayOfWeek =  DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
				 startOfDay =  LocalDate.now(TZ).with(TemporalAdjusters.previousOrSame(firstDayOfWeek)); // first day
			     endOfDay = LocalDate.now(TZ).with(TemporalAdjusters.nextOrSame(lastDayOfWeek));      // last day
				
			     query.addCriteria(Criteria.where("date").gte(startOfDay)
                        .and("startDate").lt(endOfDay));
				break;
				
			case MONTH:
				startOfDay.withDayOfMonth(1);
				endOfDay = startOfDay.withDayOfMonth(startOfDay.lengthOfMonth());
				
				query.addCriteria(Criteria.where("date").gte(startOfDay)
                        .and("startDate").lt(endOfDay));
				break;
				
			default:
				break;
		}
		  
		  List<Transport> transports = mongoTemplate.find(query, Transport.class);
		  
		  return  transports == null ? 0 : transports.size();
	  }

}
