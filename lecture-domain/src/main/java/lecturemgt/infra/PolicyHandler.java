package lecturemgt.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lecturemgt.config.kafka.KafkaProcessor;
import lecturemgt.domain.Category;
import lecturemgt.domain.CategoryChanged;
import lecturemgt.domain.CategoryRepository;

@Service
public class PolicyHandler {
     @Autowired
     CategoryRepository categoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPetReserved_displayOnTheStore(@Payload CategoryChanged categoryChanged){
        // if(!petReserved.validate())
        //     return;

        Category category = new Category();
        category.setId(categoryChanged.getId());
        category.setTitle(categoryChanged.getTitle());

        if ("INSERT".equals(categoryChanged.getJobType())) {
            categoryRepository.save(category);
        } else if ("UPDATE".equals(categoryChanged.getJobType())) {
            categoryRepository.save(category);
        } else if ("DELETE".equals(categoryChanged.getJobType())) {
            categoryRepository.delete(category);
        }     
        System.out.println("kafka in: =====================" );
        System.out.println("<" + categoryChanged.getJobType() + ":" + categoryChanged.getId() + ":" + categoryChanged.getTitle()  );
        System.out.println("kafka end: =====================" );
    }

    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverPetUpdate_updateItem(@Payload PetUpdated petUpdated){
    //     if(!petUpdated.validate())
    //         return;

    //     itemRepository.findByPetId(petUpdated.getId()).ifPresent(item->{
    //         item.setAppearance(petUpdated.getAppearance());
    //         item.setHealth(petUpdated.getEnergy());
    //         itemRepository.save(item);




    //     });
      
    // }

    ///// *** Example ****

    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverChargeStarted_recordHistory(@Payload ChargeStarted chargeStarted){
    //     if(!chargeStarted.validate())
    //         return;

    //     ChargedHistory chargedHistory = new ChargedHistory();

    //     chargedHistory.setChargedCustomer(new ChargedCustomer());
    //     chargedHistory.getChargedCustomer().setCustomerId(chargeStarted.getCustomerId());
    //     chargedHistory.getChargedCustomer().setCustomerName(chargeStarted.getCustomerName());

    //     chargedHistory.setChargerId(chargeStarted.getChargerId());
    //     chargedHistory.setStartTime(chargeStarted.getTimestamp());


    //     chargedHistoryRepository.save(chargedHistory);
    // }


    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverChargeEnded_recordHistory(@Payload ChargeEnded chargeEnded){
    //     if(!chargeEnded.validate())
    //         return;

    //     //변경 case
    //     chargedHistoryRepository.findChargerId(chargeEnded.getId()).ifPresent(chargedHistory->{
    //         chargedHistory.setEndTime(chargeEnded.getTimestamp());
    //         chargedHistory.setStatus(ChargeStatus.ENDED);
    //         chargedHistoryRepository.save(item);
    //     });


    //     // 계속 누적

    //     ChargedHistory chargedHistory = new ChargedHistory();

    //     chargedHistory.setChargedCustomer(new ChargedCustomer());
    //     chargedHistory.getChargedCustomer().setCustomerId(chargeStarted.getCustomerId());
    //     chargedHistory.getChargedCustomer().setCustomerName(chargeStarted.getCustomerName());

    //     chargedHistory.setChargerId(chargeStarted.getChargerId());
    //     chargedHistory.setTime(chargeStarted.getTimestamp());
    //     chargedHistory.setHistoryType(HistoryType.CHARGE_ENDED);


    //     chargedHistoryRepository.save(chargedHistory);
      
    // }



}