package io.github.jhipster.application.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, io.github.jhipster.application.domain.User.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Authority.class.getName());
            createCache(cm, io.github.jhipster.application.domain.User.class.getName() + ".authorities");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName());
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName());
            createCache(cm, io.github.jhipster.application.domain.FreeSpanHistory.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Anode.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Anode.class.getName() + ".anodeHists");
            createCache(cm, io.github.jhipster.application.domain.AnodeHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName());
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".anodes");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".bends");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".buckleArrestors");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".cps");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".displacements");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".freeSpans");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".freeSpanSupports");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".launchReceivers");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".pipes");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".pipejoints");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".pipelines");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".pipelineSections");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".tees");
            createCache(cm, io.github.jhipster.application.domain.BaseClass.class.getName() + ".valves");
            createCache(cm, io.github.jhipster.application.domain.Bend.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Bend.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.BendHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.BuckleArrestor.class.getName());
            createCache(cm, io.github.jhipster.application.domain.BuckleArrestor.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.BuckleArrestorHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Cps.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Cps.class.getName() + ".cpsHists");
            createCache(cm, io.github.jhipster.application.domain.Cps.class.getName() + ".cpsRanges");
            createCache(cm, io.github.jhipster.application.domain.CpsHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.CpsRange.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Displacement.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Displacement.class.getName() + ".displacementHists");
            createCache(cm, io.github.jhipster.application.domain.DisplacementHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.FreeSpan.class.getName());
            createCache(cm, io.github.jhipster.application.domain.FreeSpan.class.getName() + ".freeSpanHists");
            createCache(cm, io.github.jhipster.application.domain.FreeSpanHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.FreeSpanSupport.class.getName());
            createCache(cm, io.github.jhipster.application.domain.FreeSpanSupport.class.getName() + ".freeSpanSupportHists");
            createCache(cm, io.github.jhipster.application.domain.FreeSpanSupportHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.LaunchReceiver.class.getName());
            createCache(cm, io.github.jhipster.application.domain.LaunchReceiver.class.getName() + ".launchReceiverHists");
            createCache(cm, io.github.jhipster.application.domain.LaunchReceiverHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListAnodeBraceleteType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListAnodeBraceleteType.class.getName() + ".anodeHists");
            createCache(cm, io.github.jhipster.application.domain.ListBendManufacturer.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListBendManufacturer.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListBendSpecification.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListBendSpecification.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListBendType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListBendType.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListBoundaryCondUcase.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListBucklarrManufacturer.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListBucklarrManufacturer.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListBucklarrSpecification.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListBucklarrSpecification.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListBucklarrType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListBucklarrType.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListClayType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListClcKind.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListClcLvl.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListClcResult.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListClcType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListDfctGroup.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListDfctGroup.class.getName() + ".listDfctTypes");
            createCache(cm, io.github.jhipster.application.domain.ListDfctPosType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListDfctType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListEffAxforceUcase.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListEnvDirection.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListEnvPoint.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListExternalCoating.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListExternalCoating.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListExternalCoating.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListExternalCoating.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListExternalCoating.class.getName() + ".pipejointHists");
            createCache(cm, io.github.jhipster.application.domain.ListExternalCoating.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListExternalCoating.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListFabricationType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListFabricationType.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListFabricationType.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListFabricationType.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListFabricationType.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListFabricationType.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListIliPigType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListInternalCoating.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListInternalCoating.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListInternalCoating.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListInternalCoating.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListInternalCoating.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListInternalCoating.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListInternalPressUcase.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListLongSeamWeldType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListLongSeamWeldType.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListLongSeamWeldType.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListLongSeamWeldType.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListLongSeamWeldType.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListLongSeamWeldType.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName() + ".anodeHists");
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName() + ".pipejointHists");
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListMaterial.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListMillLocation.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListMillLocation.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListMillLocation.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListMillLocation.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListMillLocation.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListMillLocation.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListMinpressUcase.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListNominalWallThickness.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListNominalWallThickness.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListNominalWallThickness.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListNominalWallThickness.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListNominalWallThickness.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListNominalWallThickness.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".cpsHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".freeSpanHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".freeSpanSupportHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".launchReceiverHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".pipejointHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".pipelineHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectStatus.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListObjectType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListObjectType.class.getName() + ".baseClasses");
            createCache(cm, io.github.jhipster.application.domain.ListPipeManufacturer.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListPipeManufacturer.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListPipeSpecification.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListPipeSpecification.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListPipejointSpecification.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListPipejointSpecification.class.getName() + ".pipejointHists");
            createCache(cm, io.github.jhipster.application.domain.ListPipejointType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListPipejointType.class.getName() + ".pipejointHists");
            createCache(cm, io.github.jhipster.application.domain.ListPipelineLocation.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListPipelineLocation.class.getName() + ".pipelineHists");
            createCache(cm, io.github.jhipster.application.domain.ListRiskConsequence.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListRiskProbability.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListSafetyClass.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListSafetyClass.class.getName() + ".pipelineSections");
            createCache(cm, io.github.jhipster.application.domain.ListSandType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListSoilType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListSteelGrade.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListSteelGrade.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.ListSteelGrade.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.ListSteelGrade.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.ListSteelGrade.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListSteelGrade.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListTeeManufacturer.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListTeeManufacturer.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListTeeSpecification.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListTeeSpecification.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListTeeType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListTeeType.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.ListThreat.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListThreatGroup.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListThreatGroup.class.getName() + ".listThreats");
            createCache(cm, io.github.jhipster.application.domain.ListValveFunction.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListValveFunction.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListValveManufacturer.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListValveManufacturer.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListValveSpecification.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListValveSpecification.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListValveType.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListValveType.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ListWrkKind.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListWrkKind.class.getName() + ".listWrkStatuses");
            createCache(cm, io.github.jhipster.application.domain.ListWrkPurpose.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListWrkStatus.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ListWrkStatus.class.getName() + ".anodeHists");
            createCache(cm, io.github.jhipster.application.domain.ListWrkcmmsStatus.class.getName());
            createCache(cm, io.github.jhipster.application.domain.MetaList.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Pipe.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Pipe.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.PipeHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Pipejoint.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Pipejoint.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.Pipejoint.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.Pipejoint.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.Pipejoint.class.getName() + ".pipejointHists");
            createCache(cm, io.github.jhipster.application.domain.Pipejoint.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.Pipejoint.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.PipejointHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Pipeline.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Pipeline.class.getName() + ".launchReceiverHists");
            createCache(cm, io.github.jhipster.application.domain.Pipeline.class.getName() + ".pipelineHists");
            createCache(cm, io.github.jhipster.application.domain.Pipeline.class.getName() + ".pipelineSections");
            createCache(cm, io.github.jhipster.application.domain.PipelineHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".anodeHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".bendHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".buckleArrestorHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".cpsHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".cpsRanges");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".displacementHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".freeSpanHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".freeSpanSupportHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".pipeHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSection.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.PipelineSegment.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Tee.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Tee.class.getName() + ".teeHists");
            createCache(cm, io.github.jhipster.application.domain.TeeHist.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Valve.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Valve.class.getName() + ".valveHists");
            createCache(cm, io.github.jhipster.application.domain.ValveHist.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }
}
