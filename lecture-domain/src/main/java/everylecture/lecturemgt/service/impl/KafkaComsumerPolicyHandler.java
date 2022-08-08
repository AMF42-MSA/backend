package everylecture.lecturemgt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import everylecture.lecturemgt.config.kafka.KafkaProcessor;
import everylecture.lecturemgt.domain.Category;
import everylecture.lecturemgt.domain.CategoryRepository;
import everylecture.lecturemgt.service.vo.CategoryChanged;

@Service
public class KafkaComsumerPolicyHandler {
    private final Logger log = LoggerFactory.getLogger(KafkaComsumerPolicyHandler.class);

	@Autowired
     CategoryRepository categoryRepository;

    @StreamListener(KafkaProcessor.IN_categoryChanged)
    public void whatever(@Payload String eventString){
    	log.debug("whatever:{}",eventString);
    	log.debug("whatever:  한글로그");
    	
    }

    @StreamListener(KafkaProcessor.IN_categoryChanged)
    public void wheneverPetReserved_displayOnTheStore(@Payload CategoryChanged categoryChanged){
    	log.debug("wheneverPetReserved_displayOnTheStore:{}",categoryChanged.toJson());

    	//임시로 누가 호출하는지 확인하기 위한 stacktrace 출력
//    	try {
//    		throw new Exception("테스트");
//    	} catch (Exception e) {
//    		log.debug("호출결로확인", e);
//    		16:33:23.198 [KafkaConsumerDestination{consumerDestinationName='categoryChanged', partitions=1, dlqName='null'}.container-0-C-1] DEBUG e.l.s.i.KafkaComsumerPolicyHandler  :36 - 호출결로확인
//    		java.lang.Exception: 테스트
//    		        at everylecture.lecturemgt.service.impl.KafkaComsumerPolicyHandler.wheneverPetReserved_displayOnTheStore(KafkaComsumerPolicyHandler.java:34) ~[classes!/:0.0.1-SNAPSHOT]
//    		        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
//    		        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64) ~[na:na]
//    		        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
//    		        at java.base/java.lang.reflect.Method.invoke(Method.java:564) ~[na:na]
//    		        at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:171) ~[spring-messaging-5.2.7.RELEASE.jar!/:5.2.7.RELEASE]
//    		        at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:120) ~[spring-messaging-5.2.7.RELEASE.jar!/:5.2.7.RELEASE]
//    		        at org.springframework.cloud.stream.binding.StreamListenerMessageHandler.handleRequestMessage(StreamListenerMessageHandler.java:55) ~[spring-cloud-stream-3.0.13.RELEASE.jar!/:3.0.13.RELEASE]
//    		        at org.springframework.integration.handler.AbstractReplyProducingMessageHandler.handleMessageInternal(AbstractReplyProducingMessageHandler.java:134) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.handler.AbstractMessageHandler.handleMessage(AbstractMessageHandler.java:69) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.cloud.stream.binding.DispatchingStreamListenerMessageHandler.handleRequestMessage(DispatchingStreamListenerMessageHandler.java:88) ~[spring-cloud-stream-3.0.13.RELEASE.jar!/:3.0.13.RELEASE]
//    		        at org.springframework.integration.handler.AbstractReplyProducingMessageHandler.handleMessageInternal(AbstractReplyProducingMessageHandler.java:134) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.handler.AbstractMessageHandler.handleMessage(AbstractMessageHandler.java:62) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.dispatcher.AbstractDispatcher.tryOptimizedDispatch(AbstractDispatcher.java:115) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.dispatcher.UnicastingDispatcher.doDispatch(UnicastingDispatcher.java:133) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.dispatcher.UnicastingDispatcher.dispatch(UnicastingDispatcher.java:106) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.channel.AbstractSubscribableChannel.doSend(AbstractSubscribableChannel.java:72) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.channel.AbstractMessageChannel.send(AbstractMessageChannel.java:570) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.channel.AbstractMessageChannel.send(AbstractMessageChannel.java:520) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.messaging.core.GenericMessagingTemplate.doSend(GenericMessagingTemplate.java:187) ~[spring-messaging-5.2.7.RELEASE.jar!/:5.2.7.RELEASE]
//    		        at org.springframework.messaging.core.GenericMessagingTemplate.doSend(GenericMessagingTemplate.java:166) ~[spring-messaging-5.2.7.RELEASE.jar!/:5.2.7.RELEASE]
//    		        at org.springframework.messaging.core.GenericMessagingTemplate.doSend(GenericMessagingTemplate.java:47) ~[spring-messaging-5.2.7.RELEASE.jar!/:5.2.7.RELEASE]
//    		        at org.springframework.messaging.core.AbstractMessageSendingTemplate.send(AbstractMessageSendingTemplate.java:109) ~[spring-messaging-5.2.7.RELEASE.jar!/:5.2.7.RELEASE]
//    		        at org.springframework.integration.endpoint.MessageProducerSupport.sendMessage(MessageProducerSupport.java:208) ~[spring-integration-core-5.3.1.RELEASE.jar!/:5.3.1.RELEASE]
//    		        at org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter.sendMessageIfAny(KafkaMessageDrivenChannelAdapter.java:390) ~[spring-integration-kafka-3.3.1.RELEASE.jar!/:3.3.1.RELEASE]
//    	}
    	// if(!petReserved.validate())
        //     return;

        Category category = new Category();
        category.setCategoryId(categoryChanged.getCategoryId());
        category.setCategoryName(categoryChanged.getCategoryName());

        if ("INSERT".equals(categoryChanged.getJobType())) {
            categoryRepository.save(category);
        } else if ("UPDATE".equals(categoryChanged.getJobType())) {
            categoryRepository.save(category);
        } else if ("DELETE".equals(categoryChanged.getJobType())) {
            categoryRepository.delete(category);
        }     
        log.debug("ENd:{}",categoryChanged);
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