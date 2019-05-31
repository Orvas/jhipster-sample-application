package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.Tee;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListTeeType;
import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.ListTeeManufacturer;
import io.github.jhipster.application.domain.ListTeeSpecification;
import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.TeeHistRepository;
import io.github.jhipster.application.service.TeeHistService;
import io.github.jhipster.application.service.dto.TeeHistDTO;
import io.github.jhipster.application.service.mapper.TeeHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.TeeHistCriteria;
import io.github.jhipster.application.service.TeeHistQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link TeeHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TeeHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIAMETER_OUTER_STEEL = 1;
    private static final Integer UPDATED_DIAMETER_OUTER_STEEL = 2;

    private static final Integer DEFAULT_DIAMETER_OUTER_STEEL_BR = 1;
    private static final Integer UPDATED_DIAMETER_OUTER_STEEL_BR = 2;

    private static final BigDecimal DEFAULT_INTERNAL_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTERNAL_COAT_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXTERNAL_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTERNAL_COAT_THICKNESS = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CONCR_COATING = 1;
    private static final Integer UPDATED_IS_CONCR_COATING = 2;

    private static final BigDecimal DEFAULT_CONCR_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONCR_COAT_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONCR_COAT_DENSITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONCR_COAT_DENSITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MEAS_WALL_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_MEAS_WALL_THICKNESS = new BigDecimal(2);

    private static final Integer DEFAULT_LENGTH = 1;
    private static final Integer UPDATED_LENGTH = 2;

    private static final BigDecimal DEFAULT_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SMTS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SMTS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SMYS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SMYS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDBM = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDBM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDAF = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDAF = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDCS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDCS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ALLOW_TENS_STRAIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_ALLOW_TENS_STRAIN = new BigDecimal(2);

    private static final Integer DEFAULT_CORROSION_ALLOWANCE = 1;
    private static final Integer UPDATED_CORROSION_ALLOWANCE = 2;

    private static final Integer DEFAULT_FABRICATION_ALLOWANCE = 1;
    private static final Integer UPDATED_FABRICATION_ALLOWANCE = 2;

    private static final Integer DEFAULT_IS_BURIAL = 1;
    private static final Integer UPDATED_IS_BURIAL = 2;

    private static final Integer DEFAULT_BURIAL_DEPTH = 1;
    private static final Integer UPDATED_BURIAL_DEPTH = 2;

    private static final Integer DEFAULT_FACT_BURIAL_DEPTH = 1;
    private static final Integer UPDATED_FACT_BURIAL_DEPTH = 2;

    private static final LocalDate DEFAULT_DATE_MANUFACTURED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MANUFACTURED = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MILL_TEST_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MILL_TEST_PRESSURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DESIGN_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_PRESSURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INCIDENTAL_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCIDENTAL_PRESSURE = new BigDecimal(2);

    private static final LocalDate DEFAULT_DATE_INSTALLED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_INSTALLED = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_SEAM_ORIENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAM_ORIENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SEAM_ANGLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAM_ANGLE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AZIMUTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_AZIMUTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SEAB_INSTALL_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAB_INSTALL_TEMP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VERTICAL_ANGLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VERTICAL_ANGLE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEAT_TREAT_DURAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEAT_TREAT_DURAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_DESIGN_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_DESIGN_TEMP = new BigDecimal(2);

    private static final String DEFAULT_HEAT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_HEAT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_COORD = "AAAAAAAAAA";
    private static final String UPDATED_COORD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DESIGN_COORD = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_COORD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_INST = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_INST = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private TeeHistRepository teeHistRepository;

    @Autowired
    private TeeHistMapper teeHistMapper;

    @Autowired
    private TeeHistService teeHistService;

    @Autowired
    private TeeHistQueryService teeHistQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTeeHistMockMvc;

    private TeeHist teeHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TeeHistResource teeHistResource = new TeeHistResource(teeHistService, teeHistQueryService);
        this.restTeeHistMockMvc = MockMvcBuilders.standaloneSetup(teeHistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeeHist createEntity(EntityManager em) {
        TeeHist teeHist = new TeeHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .name(DEFAULT_NAME)
            .diameterOuterSteel(DEFAULT_DIAMETER_OUTER_STEEL)
            .diameterOuterSteelBr(DEFAULT_DIAMETER_OUTER_STEEL_BR)
            .internalCoatThickness(DEFAULT_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(DEFAULT_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(DEFAULT_IS_CONCR_COATING)
            .concrCoatThickness(DEFAULT_CONCR_COAT_THICKNESS)
            .concrCoatDensity(DEFAULT_CONCR_COAT_DENSITY)
            .measWallThickness(DEFAULT_MEAS_WALL_THICKNESS)
            .length(DEFAULT_LENGTH)
            .weight(DEFAULT_WEIGHT)
            .smts(DEFAULT_SMTS)
            .smys(DEFAULT_SMYS)
            .sdbm(DEFAULT_SDBM)
            .sdaf(DEFAULT_SDAF)
            .sdcs(DEFAULT_SDCS)
            .allowTensStrain(DEFAULT_ALLOW_TENS_STRAIN)
            .corrosionAllowance(DEFAULT_CORROSION_ALLOWANCE)
            .fabricationAllowance(DEFAULT_FABRICATION_ALLOWANCE)
            .isBurial(DEFAULT_IS_BURIAL)
            .burialDepth(DEFAULT_BURIAL_DEPTH)
            .factBurialDepth(DEFAULT_FACT_BURIAL_DEPTH)
            .dateManufactured(DEFAULT_DATE_MANUFACTURED)
            .millTestPressure(DEFAULT_MILL_TEST_PRESSURE)
            .designPressure(DEFAULT_DESIGN_PRESSURE)
            .incidentalPressure(DEFAULT_INCIDENTAL_PRESSURE)
            .dateInstalled(DEFAULT_DATE_INSTALLED)
            .seamOrient(DEFAULT_SEAM_ORIENT)
            .seamAngle(DEFAULT_SEAM_ANGLE)
            .azimuth(DEFAULT_AZIMUTH)
            .seabInstallTemp(DEFAULT_SEAB_INSTALL_TEMP)
            .verticalAngle(DEFAULT_VERTICAL_ANGLE)
            .heatTreatDurat(DEFAULT_HEAT_TREAT_DURAT)
            .maxDesignTemp(DEFAULT_MAX_DESIGN_TEMP)
            .heatNumber(DEFAULT_HEAT_NUMBER)
            .coord(DEFAULT_COORD)
            .designCoord(DEFAULT_DESIGN_COORD)
            .kpInst(DEFAULT_KP_INST)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        Tee tee;
        if (TestUtil.findAll(em, Tee.class).isEmpty()) {
            tee = TeeResourceIT.createEntity(em);
            em.persist(tee);
            em.flush();
        } else {
            tee = TestUtil.findAll(em, Tee.class).get(0);
        }
        teeHist.setId(tee);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        teeHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        teeHist.setIdStatus(listObjectStatus);
        return teeHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeeHist createUpdatedEntity(EntityManager em) {
        TeeHist teeHist = new TeeHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .name(UPDATED_NAME)
            .diameterOuterSteel(UPDATED_DIAMETER_OUTER_STEEL)
            .diameterOuterSteelBr(UPDATED_DIAMETER_OUTER_STEEL_BR)
            .internalCoatThickness(UPDATED_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(UPDATED_IS_CONCR_COATING)
            .concrCoatThickness(UPDATED_CONCR_COAT_THICKNESS)
            .concrCoatDensity(UPDATED_CONCR_COAT_DENSITY)
            .measWallThickness(UPDATED_MEAS_WALL_THICKNESS)
            .length(UPDATED_LENGTH)
            .weight(UPDATED_WEIGHT)
            .smts(UPDATED_SMTS)
            .smys(UPDATED_SMYS)
            .sdbm(UPDATED_SDBM)
            .sdaf(UPDATED_SDAF)
            .sdcs(UPDATED_SDCS)
            .allowTensStrain(UPDATED_ALLOW_TENS_STRAIN)
            .corrosionAllowance(UPDATED_CORROSION_ALLOWANCE)
            .fabricationAllowance(UPDATED_FABRICATION_ALLOWANCE)
            .isBurial(UPDATED_IS_BURIAL)
            .burialDepth(UPDATED_BURIAL_DEPTH)
            .factBurialDepth(UPDATED_FACT_BURIAL_DEPTH)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
            .incidentalPressure(UPDATED_INCIDENTAL_PRESSURE)
            .dateInstalled(UPDATED_DATE_INSTALLED)
            .seamOrient(UPDATED_SEAM_ORIENT)
            .seamAngle(UPDATED_SEAM_ANGLE)
            .azimuth(UPDATED_AZIMUTH)
            .seabInstallTemp(UPDATED_SEAB_INSTALL_TEMP)
            .verticalAngle(UPDATED_VERTICAL_ANGLE)
            .heatTreatDurat(UPDATED_HEAT_TREAT_DURAT)
            .maxDesignTemp(UPDATED_MAX_DESIGN_TEMP)
            .heatNumber(UPDATED_HEAT_NUMBER)
            .coord(UPDATED_COORD)
            .designCoord(UPDATED_DESIGN_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        Tee tee;
        if (TestUtil.findAll(em, Tee.class).isEmpty()) {
            tee = TeeResourceIT.createUpdatedEntity(em);
            em.persist(tee);
            em.flush();
        } else {
            tee = TestUtil.findAll(em, Tee.class).get(0);
        }
        teeHist.setId(tee);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        teeHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        teeHist.setIdStatus(listObjectStatus);
        return teeHist;
    }

    @BeforeEach
    public void initTest() {
        teeHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createTeeHist() throws Exception {
        int databaseSizeBeforeCreate = teeHistRepository.findAll().size();

        // Create the TeeHist
        TeeHistDTO teeHistDTO = teeHistMapper.toDto(teeHist);
        restTeeHistMockMvc.perform(post("/api/tee-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeHistDTO)))
            .andExpect(status().isCreated());

        // Validate the TeeHist in the database
        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeCreate + 1);
        TeeHist testTeeHist = teeHistList.get(teeHistList.size() - 1);
        assertThat(testTeeHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testTeeHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testTeeHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testTeeHist.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTeeHist.getDiameterOuterSteel()).isEqualTo(DEFAULT_DIAMETER_OUTER_STEEL);
        assertThat(testTeeHist.getDiameterOuterSteelBr()).isEqualTo(DEFAULT_DIAMETER_OUTER_STEEL_BR);
        assertThat(testTeeHist.getInternalCoatThickness()).isEqualTo(DEFAULT_INTERNAL_COAT_THICKNESS);
        assertThat(testTeeHist.getExternalCoatThickness()).isEqualTo(DEFAULT_EXTERNAL_COAT_THICKNESS);
        assertThat(testTeeHist.getIsConcrCoating()).isEqualTo(DEFAULT_IS_CONCR_COATING);
        assertThat(testTeeHist.getConcrCoatThickness()).isEqualTo(DEFAULT_CONCR_COAT_THICKNESS);
        assertThat(testTeeHist.getConcrCoatDensity()).isEqualTo(DEFAULT_CONCR_COAT_DENSITY);
        assertThat(testTeeHist.getMeasWallThickness()).isEqualTo(DEFAULT_MEAS_WALL_THICKNESS);
        assertThat(testTeeHist.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testTeeHist.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testTeeHist.getSmts()).isEqualTo(DEFAULT_SMTS);
        assertThat(testTeeHist.getSmys()).isEqualTo(DEFAULT_SMYS);
        assertThat(testTeeHist.getSdbm()).isEqualTo(DEFAULT_SDBM);
        assertThat(testTeeHist.getSdaf()).isEqualTo(DEFAULT_SDAF);
        assertThat(testTeeHist.getSdcs()).isEqualTo(DEFAULT_SDCS);
        assertThat(testTeeHist.getAllowTensStrain()).isEqualTo(DEFAULT_ALLOW_TENS_STRAIN);
        assertThat(testTeeHist.getCorrosionAllowance()).isEqualTo(DEFAULT_CORROSION_ALLOWANCE);
        assertThat(testTeeHist.getFabricationAllowance()).isEqualTo(DEFAULT_FABRICATION_ALLOWANCE);
        assertThat(testTeeHist.getIsBurial()).isEqualTo(DEFAULT_IS_BURIAL);
        assertThat(testTeeHist.getBurialDepth()).isEqualTo(DEFAULT_BURIAL_DEPTH);
        assertThat(testTeeHist.getFactBurialDepth()).isEqualTo(DEFAULT_FACT_BURIAL_DEPTH);
        assertThat(testTeeHist.getDateManufactured()).isEqualTo(DEFAULT_DATE_MANUFACTURED);
        assertThat(testTeeHist.getMillTestPressure()).isEqualTo(DEFAULT_MILL_TEST_PRESSURE);
        assertThat(testTeeHist.getDesignPressure()).isEqualTo(DEFAULT_DESIGN_PRESSURE);
        assertThat(testTeeHist.getIncidentalPressure()).isEqualTo(DEFAULT_INCIDENTAL_PRESSURE);
        assertThat(testTeeHist.getDateInstalled()).isEqualTo(DEFAULT_DATE_INSTALLED);
        assertThat(testTeeHist.getSeamOrient()).isEqualTo(DEFAULT_SEAM_ORIENT);
        assertThat(testTeeHist.getSeamAngle()).isEqualTo(DEFAULT_SEAM_ANGLE);
        assertThat(testTeeHist.getAzimuth()).isEqualTo(DEFAULT_AZIMUTH);
        assertThat(testTeeHist.getSeabInstallTemp()).isEqualTo(DEFAULT_SEAB_INSTALL_TEMP);
        assertThat(testTeeHist.getVerticalAngle()).isEqualTo(DEFAULT_VERTICAL_ANGLE);
        assertThat(testTeeHist.getHeatTreatDurat()).isEqualTo(DEFAULT_HEAT_TREAT_DURAT);
        assertThat(testTeeHist.getMaxDesignTemp()).isEqualTo(DEFAULT_MAX_DESIGN_TEMP);
        assertThat(testTeeHist.getHeatNumber()).isEqualTo(DEFAULT_HEAT_NUMBER);
        assertThat(testTeeHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testTeeHist.getDesignCoord()).isEqualTo(DEFAULT_DESIGN_COORD);
        assertThat(testTeeHist.getKpInst()).isEqualTo(DEFAULT_KP_INST);
        assertThat(testTeeHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testTeeHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTeeHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testTeeHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testTeeHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testTeeHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testTeeHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createTeeHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = teeHistRepository.findAll().size();

        // Create the TeeHist with an existing ID
        teeHist.setId(1L);
        TeeHistDTO teeHistDTO = teeHistMapper.toDto(teeHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeeHistMockMvc.perform(post("/api/tee-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TeeHist in the database
        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = teeHistRepository.findAll().size();
        // set the field null
        teeHist.setDateFrom(null);

        // Create the TeeHist, which fails.
        TeeHistDTO teeHistDTO = teeHistMapper.toDto(teeHist);

        restTeeHistMockMvc.perform(post("/api/tee-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeHistDTO)))
            .andExpect(status().isBadRequest());

        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = teeHistRepository.findAll().size();
        // set the field null
        teeHist.setDateTo(null);

        // Create the TeeHist, which fails.
        TeeHistDTO teeHistDTO = teeHistMapper.toDto(teeHist);

        restTeeHistMockMvc.perform(post("/api/tee-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeHistDTO)))
            .andExpect(status().isBadRequest());

        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = teeHistRepository.findAll().size();
        // set the field null
        teeHist.setIsCurrentFlag(null);

        // Create the TeeHist, which fails.
        TeeHistDTO teeHistDTO = teeHistMapper.toDto(teeHist);

        restTeeHistMockMvc.perform(post("/api/tee-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeHistDTO)))
            .andExpect(status().isBadRequest());

        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTeeHists() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList
        restTeeHistMockMvc.perform(get("/api/tee-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teeHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].diameterOuterSteel").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL)))
            .andExpect(jsonPath("$.[*].diameterOuterSteelBr").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL_BR)))
            .andExpect(jsonPath("$.[*].internalCoatThickness").value(hasItem(DEFAULT_INTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].isConcrCoating").value(hasItem(DEFAULT_IS_CONCR_COATING)))
            .andExpect(jsonPath("$.[*].concrCoatThickness").value(hasItem(DEFAULT_CONCR_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].concrCoatDensity").value(hasItem(DEFAULT_CONCR_COAT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].measWallThickness").value(hasItem(DEFAULT_MEAS_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].smts").value(hasItem(DEFAULT_SMTS.intValue())))
            .andExpect(jsonPath("$.[*].smys").value(hasItem(DEFAULT_SMYS.intValue())))
            .andExpect(jsonPath("$.[*].sdbm").value(hasItem(DEFAULT_SDBM.intValue())))
            .andExpect(jsonPath("$.[*].sdaf").value(hasItem(DEFAULT_SDAF.intValue())))
            .andExpect(jsonPath("$.[*].sdcs").value(hasItem(DEFAULT_SDCS.intValue())))
            .andExpect(jsonPath("$.[*].allowTensStrain").value(hasItem(DEFAULT_ALLOW_TENS_STRAIN.intValue())))
            .andExpect(jsonPath("$.[*].corrosionAllowance").value(hasItem(DEFAULT_CORROSION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].fabricationAllowance").value(hasItem(DEFAULT_FABRICATION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].burialDepth").value(hasItem(DEFAULT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].factBurialDepth").value(hasItem(DEFAULT_FACT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].incidentalPressure").value(hasItem(DEFAULT_INCIDENTAL_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].dateInstalled").value(hasItem(DEFAULT_DATE_INSTALLED.toString())))
            .andExpect(jsonPath("$.[*].seamOrient").value(hasItem(DEFAULT_SEAM_ORIENT.intValue())))
            .andExpect(jsonPath("$.[*].seamAngle").value(hasItem(DEFAULT_SEAM_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].azimuth").value(hasItem(DEFAULT_AZIMUTH.intValue())))
            .andExpect(jsonPath("$.[*].seabInstallTemp").value(hasItem(DEFAULT_SEAB_INSTALL_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].verticalAngle").value(hasItem(DEFAULT_VERTICAL_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].heatTreatDurat").value(hasItem(DEFAULT_HEAT_TREAT_DURAT.intValue())))
            .andExpect(jsonPath("$.[*].maxDesignTemp").value(hasItem(DEFAULT_MAX_DESIGN_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].heatNumber").value(hasItem(DEFAULT_HEAT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD.toString())))
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getTeeHist() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get the teeHist
        restTeeHistMockMvc.perform(get("/api/tee-hists/{id}", teeHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(teeHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.diameterOuterSteel").value(DEFAULT_DIAMETER_OUTER_STEEL))
            .andExpect(jsonPath("$.diameterOuterSteelBr").value(DEFAULT_DIAMETER_OUTER_STEEL_BR))
            .andExpect(jsonPath("$.internalCoatThickness").value(DEFAULT_INTERNAL_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.externalCoatThickness").value(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.isConcrCoating").value(DEFAULT_IS_CONCR_COATING))
            .andExpect(jsonPath("$.concrCoatThickness").value(DEFAULT_CONCR_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.concrCoatDensity").value(DEFAULT_CONCR_COAT_DENSITY.intValue()))
            .andExpect(jsonPath("$.measWallThickness").value(DEFAULT_MEAS_WALL_THICKNESS.intValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.smts").value(DEFAULT_SMTS.intValue()))
            .andExpect(jsonPath("$.smys").value(DEFAULT_SMYS.intValue()))
            .andExpect(jsonPath("$.sdbm").value(DEFAULT_SDBM.intValue()))
            .andExpect(jsonPath("$.sdaf").value(DEFAULT_SDAF.intValue()))
            .andExpect(jsonPath("$.sdcs").value(DEFAULT_SDCS.intValue()))
            .andExpect(jsonPath("$.allowTensStrain").value(DEFAULT_ALLOW_TENS_STRAIN.intValue()))
            .andExpect(jsonPath("$.corrosionAllowance").value(DEFAULT_CORROSION_ALLOWANCE))
            .andExpect(jsonPath("$.fabricationAllowance").value(DEFAULT_FABRICATION_ALLOWANCE))
            .andExpect(jsonPath("$.isBurial").value(DEFAULT_IS_BURIAL))
            .andExpect(jsonPath("$.burialDepth").value(DEFAULT_BURIAL_DEPTH))
            .andExpect(jsonPath("$.factBurialDepth").value(DEFAULT_FACT_BURIAL_DEPTH))
            .andExpect(jsonPath("$.dateManufactured").value(DEFAULT_DATE_MANUFACTURED.toString()))
            .andExpect(jsonPath("$.millTestPressure").value(DEFAULT_MILL_TEST_PRESSURE.intValue()))
            .andExpect(jsonPath("$.designPressure").value(DEFAULT_DESIGN_PRESSURE.intValue()))
            .andExpect(jsonPath("$.incidentalPressure").value(DEFAULT_INCIDENTAL_PRESSURE.intValue()))
            .andExpect(jsonPath("$.dateInstalled").value(DEFAULT_DATE_INSTALLED.toString()))
            .andExpect(jsonPath("$.seamOrient").value(DEFAULT_SEAM_ORIENT.intValue()))
            .andExpect(jsonPath("$.seamAngle").value(DEFAULT_SEAM_ANGLE.intValue()))
            .andExpect(jsonPath("$.azimuth").value(DEFAULT_AZIMUTH.intValue()))
            .andExpect(jsonPath("$.seabInstallTemp").value(DEFAULT_SEAB_INSTALL_TEMP.intValue()))
            .andExpect(jsonPath("$.verticalAngle").value(DEFAULT_VERTICAL_ANGLE.intValue()))
            .andExpect(jsonPath("$.heatTreatDurat").value(DEFAULT_HEAT_TREAT_DURAT.intValue()))
            .andExpect(jsonPath("$.maxDesignTemp").value(DEFAULT_MAX_DESIGN_TEMP.intValue()))
            .andExpect(jsonPath("$.heatNumber").value(DEFAULT_HEAT_NUMBER.toString()))
            .andExpect(jsonPath("$.coord").value(DEFAULT_COORD.toString()))
            .andExpect(jsonPath("$.designCoord").value(DEFAULT_DESIGN_COORD.intValue()))
            .andExpect(jsonPath("$.kpInst").value(DEFAULT_KP_INST.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultTeeHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the teeHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultTeeHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultTeeHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the teeHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultTeeHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateFrom is not null
        defaultTeeHistShouldBeFound("dateFrom.specified=true");

        // Get all the teeHistList where dateFrom is null
        defaultTeeHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultTeeHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the teeHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultTeeHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultTeeHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the teeHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultTeeHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateTo equals to DEFAULT_DATE_TO
        defaultTeeHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the teeHistList where dateTo equals to UPDATED_DATE_TO
        defaultTeeHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultTeeHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the teeHistList where dateTo equals to UPDATED_DATE_TO
        defaultTeeHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateTo is not null
        defaultTeeHistShouldBeFound("dateTo.specified=true");

        // Get all the teeHistList where dateTo is null
        defaultTeeHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultTeeHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the teeHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultTeeHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultTeeHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the teeHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultTeeHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where idWrk equals to DEFAULT_ID_WRK
        defaultTeeHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the teeHistList where idWrk equals to UPDATED_ID_WRK
        defaultTeeHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultTeeHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the teeHistList where idWrk equals to UPDATED_ID_WRK
        defaultTeeHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where idWrk is not null
        defaultTeeHistShouldBeFound("idWrk.specified=true");

        // Get all the teeHistList where idWrk is null
        defaultTeeHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultTeeHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the teeHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultTeeHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultTeeHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the teeHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultTeeHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where name equals to DEFAULT_NAME
        defaultTeeHistShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the teeHistList where name equals to UPDATED_NAME
        defaultTeeHistShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where name in DEFAULT_NAME or UPDATED_NAME
        defaultTeeHistShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the teeHistList where name equals to UPDATED_NAME
        defaultTeeHistShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where name is not null
        defaultTeeHistShouldBeFound("name.specified=true");

        // Get all the teeHistList where name is null
        defaultTeeHistShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteel equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldBeFound("diameterOuterSteel.equals=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the teeHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldNotBeFound("diameterOuterSteel.equals=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteel in DEFAULT_DIAMETER_OUTER_STEEL or UPDATED_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldBeFound("diameterOuterSteel.in=" + DEFAULT_DIAMETER_OUTER_STEEL + "," + UPDATED_DIAMETER_OUTER_STEEL);

        // Get all the teeHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldNotBeFound("diameterOuterSteel.in=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteel is not null
        defaultTeeHistShouldBeFound("diameterOuterSteel.specified=true");

        // Get all the teeHistList where diameterOuterSteel is null
        defaultTeeHistShouldNotBeFound("diameterOuterSteel.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteel greater than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldBeFound("diameterOuterSteel.greaterOrEqualThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the teeHistList where diameterOuterSteel greater than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldNotBeFound("diameterOuterSteel.greaterOrEqualThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteel less than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldNotBeFound("diameterOuterSteel.lessThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the teeHistList where diameterOuterSteel less than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultTeeHistShouldBeFound("diameterOuterSteel.lessThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelBrIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteelBr equals to DEFAULT_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldBeFound("diameterOuterSteelBr.equals=" + DEFAULT_DIAMETER_OUTER_STEEL_BR);

        // Get all the teeHistList where diameterOuterSteelBr equals to UPDATED_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldNotBeFound("diameterOuterSteelBr.equals=" + UPDATED_DIAMETER_OUTER_STEEL_BR);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelBrIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteelBr in DEFAULT_DIAMETER_OUTER_STEEL_BR or UPDATED_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldBeFound("diameterOuterSteelBr.in=" + DEFAULT_DIAMETER_OUTER_STEEL_BR + "," + UPDATED_DIAMETER_OUTER_STEEL_BR);

        // Get all the teeHistList where diameterOuterSteelBr equals to UPDATED_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldNotBeFound("diameterOuterSteelBr.in=" + UPDATED_DIAMETER_OUTER_STEEL_BR);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelBrIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteelBr is not null
        defaultTeeHistShouldBeFound("diameterOuterSteelBr.specified=true");

        // Get all the teeHistList where diameterOuterSteelBr is null
        defaultTeeHistShouldNotBeFound("diameterOuterSteelBr.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelBrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteelBr greater than or equals to DEFAULT_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldBeFound("diameterOuterSteelBr.greaterOrEqualThan=" + DEFAULT_DIAMETER_OUTER_STEEL_BR);

        // Get all the teeHistList where diameterOuterSteelBr greater than or equals to UPDATED_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldNotBeFound("diameterOuterSteelBr.greaterOrEqualThan=" + UPDATED_DIAMETER_OUTER_STEEL_BR);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDiameterOuterSteelBrIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where diameterOuterSteelBr less than or equals to DEFAULT_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldNotBeFound("diameterOuterSteelBr.lessThan=" + DEFAULT_DIAMETER_OUTER_STEEL_BR);

        // Get all the teeHistList where diameterOuterSteelBr less than or equals to UPDATED_DIAMETER_OUTER_STEEL_BR
        defaultTeeHistShouldBeFound("diameterOuterSteelBr.lessThan=" + UPDATED_DIAMETER_OUTER_STEEL_BR);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByInternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where internalCoatThickness equals to DEFAULT_INTERNAL_COAT_THICKNESS
        defaultTeeHistShouldBeFound("internalCoatThickness.equals=" + DEFAULT_INTERNAL_COAT_THICKNESS);

        // Get all the teeHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultTeeHistShouldNotBeFound("internalCoatThickness.equals=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByInternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where internalCoatThickness in DEFAULT_INTERNAL_COAT_THICKNESS or UPDATED_INTERNAL_COAT_THICKNESS
        defaultTeeHistShouldBeFound("internalCoatThickness.in=" + DEFAULT_INTERNAL_COAT_THICKNESS + "," + UPDATED_INTERNAL_COAT_THICKNESS);

        // Get all the teeHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultTeeHistShouldNotBeFound("internalCoatThickness.in=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByInternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where internalCoatThickness is not null
        defaultTeeHistShouldBeFound("internalCoatThickness.specified=true");

        // Get all the teeHistList where internalCoatThickness is null
        defaultTeeHistShouldNotBeFound("internalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByExternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where externalCoatThickness equals to DEFAULT_EXTERNAL_COAT_THICKNESS
        defaultTeeHistShouldBeFound("externalCoatThickness.equals=" + DEFAULT_EXTERNAL_COAT_THICKNESS);

        // Get all the teeHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultTeeHistShouldNotBeFound("externalCoatThickness.equals=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByExternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where externalCoatThickness in DEFAULT_EXTERNAL_COAT_THICKNESS or UPDATED_EXTERNAL_COAT_THICKNESS
        defaultTeeHistShouldBeFound("externalCoatThickness.in=" + DEFAULT_EXTERNAL_COAT_THICKNESS + "," + UPDATED_EXTERNAL_COAT_THICKNESS);

        // Get all the teeHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultTeeHistShouldNotBeFound("externalCoatThickness.in=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByExternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where externalCoatThickness is not null
        defaultTeeHistShouldBeFound("externalCoatThickness.specified=true");

        // Get all the teeHistList where externalCoatThickness is null
        defaultTeeHistShouldNotBeFound("externalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsConcrCoatingIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isConcrCoating equals to DEFAULT_IS_CONCR_COATING
        defaultTeeHistShouldBeFound("isConcrCoating.equals=" + DEFAULT_IS_CONCR_COATING);

        // Get all the teeHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultTeeHistShouldNotBeFound("isConcrCoating.equals=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsConcrCoatingIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isConcrCoating in DEFAULT_IS_CONCR_COATING or UPDATED_IS_CONCR_COATING
        defaultTeeHistShouldBeFound("isConcrCoating.in=" + DEFAULT_IS_CONCR_COATING + "," + UPDATED_IS_CONCR_COATING);

        // Get all the teeHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultTeeHistShouldNotBeFound("isConcrCoating.in=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsConcrCoatingIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isConcrCoating is not null
        defaultTeeHistShouldBeFound("isConcrCoating.specified=true");

        // Get all the teeHistList where isConcrCoating is null
        defaultTeeHistShouldNotBeFound("isConcrCoating.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsConcrCoatingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isConcrCoating greater than or equals to DEFAULT_IS_CONCR_COATING
        defaultTeeHistShouldBeFound("isConcrCoating.greaterOrEqualThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the teeHistList where isConcrCoating greater than or equals to UPDATED_IS_CONCR_COATING
        defaultTeeHistShouldNotBeFound("isConcrCoating.greaterOrEqualThan=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsConcrCoatingIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isConcrCoating less than or equals to DEFAULT_IS_CONCR_COATING
        defaultTeeHistShouldNotBeFound("isConcrCoating.lessThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the teeHistList where isConcrCoating less than or equals to UPDATED_IS_CONCR_COATING
        defaultTeeHistShouldBeFound("isConcrCoating.lessThan=" + UPDATED_IS_CONCR_COATING);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByConcrCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where concrCoatThickness equals to DEFAULT_CONCR_COAT_THICKNESS
        defaultTeeHistShouldBeFound("concrCoatThickness.equals=" + DEFAULT_CONCR_COAT_THICKNESS);

        // Get all the teeHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultTeeHistShouldNotBeFound("concrCoatThickness.equals=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByConcrCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where concrCoatThickness in DEFAULT_CONCR_COAT_THICKNESS or UPDATED_CONCR_COAT_THICKNESS
        defaultTeeHistShouldBeFound("concrCoatThickness.in=" + DEFAULT_CONCR_COAT_THICKNESS + "," + UPDATED_CONCR_COAT_THICKNESS);

        // Get all the teeHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultTeeHistShouldNotBeFound("concrCoatThickness.in=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByConcrCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where concrCoatThickness is not null
        defaultTeeHistShouldBeFound("concrCoatThickness.specified=true");

        // Get all the teeHistList where concrCoatThickness is null
        defaultTeeHistShouldNotBeFound("concrCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByConcrCoatDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where concrCoatDensity equals to DEFAULT_CONCR_COAT_DENSITY
        defaultTeeHistShouldBeFound("concrCoatDensity.equals=" + DEFAULT_CONCR_COAT_DENSITY);

        // Get all the teeHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultTeeHistShouldNotBeFound("concrCoatDensity.equals=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByConcrCoatDensityIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where concrCoatDensity in DEFAULT_CONCR_COAT_DENSITY or UPDATED_CONCR_COAT_DENSITY
        defaultTeeHistShouldBeFound("concrCoatDensity.in=" + DEFAULT_CONCR_COAT_DENSITY + "," + UPDATED_CONCR_COAT_DENSITY);

        // Get all the teeHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultTeeHistShouldNotBeFound("concrCoatDensity.in=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByConcrCoatDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where concrCoatDensity is not null
        defaultTeeHistShouldBeFound("concrCoatDensity.specified=true");

        // Get all the teeHistList where concrCoatDensity is null
        defaultTeeHistShouldNotBeFound("concrCoatDensity.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMeasWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where measWallThickness equals to DEFAULT_MEAS_WALL_THICKNESS
        defaultTeeHistShouldBeFound("measWallThickness.equals=" + DEFAULT_MEAS_WALL_THICKNESS);

        // Get all the teeHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultTeeHistShouldNotBeFound("measWallThickness.equals=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMeasWallThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where measWallThickness in DEFAULT_MEAS_WALL_THICKNESS or UPDATED_MEAS_WALL_THICKNESS
        defaultTeeHistShouldBeFound("measWallThickness.in=" + DEFAULT_MEAS_WALL_THICKNESS + "," + UPDATED_MEAS_WALL_THICKNESS);

        // Get all the teeHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultTeeHistShouldNotBeFound("measWallThickness.in=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMeasWallThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where measWallThickness is not null
        defaultTeeHistShouldBeFound("measWallThickness.specified=true");

        // Get all the teeHistList where measWallThickness is null
        defaultTeeHistShouldNotBeFound("measWallThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where length equals to DEFAULT_LENGTH
        defaultTeeHistShouldBeFound("length.equals=" + DEFAULT_LENGTH);

        // Get all the teeHistList where length equals to UPDATED_LENGTH
        defaultTeeHistShouldNotBeFound("length.equals=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByLengthIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where length in DEFAULT_LENGTH or UPDATED_LENGTH
        defaultTeeHistShouldBeFound("length.in=" + DEFAULT_LENGTH + "," + UPDATED_LENGTH);

        // Get all the teeHistList where length equals to UPDATED_LENGTH
        defaultTeeHistShouldNotBeFound("length.in=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where length is not null
        defaultTeeHistShouldBeFound("length.specified=true");

        // Get all the teeHistList where length is null
        defaultTeeHistShouldNotBeFound("length.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where length greater than or equals to DEFAULT_LENGTH
        defaultTeeHistShouldBeFound("length.greaterOrEqualThan=" + DEFAULT_LENGTH);

        // Get all the teeHistList where length greater than or equals to UPDATED_LENGTH
        defaultTeeHistShouldNotBeFound("length.greaterOrEqualThan=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where length less than or equals to DEFAULT_LENGTH
        defaultTeeHistShouldNotBeFound("length.lessThan=" + DEFAULT_LENGTH);

        // Get all the teeHistList where length less than or equals to UPDATED_LENGTH
        defaultTeeHistShouldBeFound("length.lessThan=" + UPDATED_LENGTH);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where weight equals to DEFAULT_WEIGHT
        defaultTeeHistShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the teeHistList where weight equals to UPDATED_WEIGHT
        defaultTeeHistShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultTeeHistShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the teeHistList where weight equals to UPDATED_WEIGHT
        defaultTeeHistShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where weight is not null
        defaultTeeHistShouldBeFound("weight.specified=true");

        // Get all the teeHistList where weight is null
        defaultTeeHistShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySmtsIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where smts equals to DEFAULT_SMTS
        defaultTeeHistShouldBeFound("smts.equals=" + DEFAULT_SMTS);

        // Get all the teeHistList where smts equals to UPDATED_SMTS
        defaultTeeHistShouldNotBeFound("smts.equals=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySmtsIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where smts in DEFAULT_SMTS or UPDATED_SMTS
        defaultTeeHistShouldBeFound("smts.in=" + DEFAULT_SMTS + "," + UPDATED_SMTS);

        // Get all the teeHistList where smts equals to UPDATED_SMTS
        defaultTeeHistShouldNotBeFound("smts.in=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySmtsIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where smts is not null
        defaultTeeHistShouldBeFound("smts.specified=true");

        // Get all the teeHistList where smts is null
        defaultTeeHistShouldNotBeFound("smts.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySmysIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where smys equals to DEFAULT_SMYS
        defaultTeeHistShouldBeFound("smys.equals=" + DEFAULT_SMYS);

        // Get all the teeHistList where smys equals to UPDATED_SMYS
        defaultTeeHistShouldNotBeFound("smys.equals=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySmysIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where smys in DEFAULT_SMYS or UPDATED_SMYS
        defaultTeeHistShouldBeFound("smys.in=" + DEFAULT_SMYS + "," + UPDATED_SMYS);

        // Get all the teeHistList where smys equals to UPDATED_SMYS
        defaultTeeHistShouldNotBeFound("smys.in=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySmysIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where smys is not null
        defaultTeeHistShouldBeFound("smys.specified=true");

        // Get all the teeHistList where smys is null
        defaultTeeHistShouldNotBeFound("smys.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdbmIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdbm equals to DEFAULT_SDBM
        defaultTeeHistShouldBeFound("sdbm.equals=" + DEFAULT_SDBM);

        // Get all the teeHistList where sdbm equals to UPDATED_SDBM
        defaultTeeHistShouldNotBeFound("sdbm.equals=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdbmIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdbm in DEFAULT_SDBM or UPDATED_SDBM
        defaultTeeHistShouldBeFound("sdbm.in=" + DEFAULT_SDBM + "," + UPDATED_SDBM);

        // Get all the teeHistList where sdbm equals to UPDATED_SDBM
        defaultTeeHistShouldNotBeFound("sdbm.in=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdbmIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdbm is not null
        defaultTeeHistShouldBeFound("sdbm.specified=true");

        // Get all the teeHistList where sdbm is null
        defaultTeeHistShouldNotBeFound("sdbm.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdafIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdaf equals to DEFAULT_SDAF
        defaultTeeHistShouldBeFound("sdaf.equals=" + DEFAULT_SDAF);

        // Get all the teeHistList where sdaf equals to UPDATED_SDAF
        defaultTeeHistShouldNotBeFound("sdaf.equals=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdafIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdaf in DEFAULT_SDAF or UPDATED_SDAF
        defaultTeeHistShouldBeFound("sdaf.in=" + DEFAULT_SDAF + "," + UPDATED_SDAF);

        // Get all the teeHistList where sdaf equals to UPDATED_SDAF
        defaultTeeHistShouldNotBeFound("sdaf.in=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdafIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdaf is not null
        defaultTeeHistShouldBeFound("sdaf.specified=true");

        // Get all the teeHistList where sdaf is null
        defaultTeeHistShouldNotBeFound("sdaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdcsIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdcs equals to DEFAULT_SDCS
        defaultTeeHistShouldBeFound("sdcs.equals=" + DEFAULT_SDCS);

        // Get all the teeHistList where sdcs equals to UPDATED_SDCS
        defaultTeeHistShouldNotBeFound("sdcs.equals=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdcsIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdcs in DEFAULT_SDCS or UPDATED_SDCS
        defaultTeeHistShouldBeFound("sdcs.in=" + DEFAULT_SDCS + "," + UPDATED_SDCS);

        // Get all the teeHistList where sdcs equals to UPDATED_SDCS
        defaultTeeHistShouldNotBeFound("sdcs.in=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySdcsIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where sdcs is not null
        defaultTeeHistShouldBeFound("sdcs.specified=true");

        // Get all the teeHistList where sdcs is null
        defaultTeeHistShouldNotBeFound("sdcs.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByAllowTensStrainIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where allowTensStrain equals to DEFAULT_ALLOW_TENS_STRAIN
        defaultTeeHistShouldBeFound("allowTensStrain.equals=" + DEFAULT_ALLOW_TENS_STRAIN);

        // Get all the teeHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultTeeHistShouldNotBeFound("allowTensStrain.equals=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByAllowTensStrainIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where allowTensStrain in DEFAULT_ALLOW_TENS_STRAIN or UPDATED_ALLOW_TENS_STRAIN
        defaultTeeHistShouldBeFound("allowTensStrain.in=" + DEFAULT_ALLOW_TENS_STRAIN + "," + UPDATED_ALLOW_TENS_STRAIN);

        // Get all the teeHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultTeeHistShouldNotBeFound("allowTensStrain.in=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByAllowTensStrainIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where allowTensStrain is not null
        defaultTeeHistShouldBeFound("allowTensStrain.specified=true");

        // Get all the teeHistList where allowTensStrain is null
        defaultTeeHistShouldNotBeFound("allowTensStrain.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCorrosionAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where corrosionAllowance equals to DEFAULT_CORROSION_ALLOWANCE
        defaultTeeHistShouldBeFound("corrosionAllowance.equals=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the teeHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("corrosionAllowance.equals=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCorrosionAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where corrosionAllowance in DEFAULT_CORROSION_ALLOWANCE or UPDATED_CORROSION_ALLOWANCE
        defaultTeeHistShouldBeFound("corrosionAllowance.in=" + DEFAULT_CORROSION_ALLOWANCE + "," + UPDATED_CORROSION_ALLOWANCE);

        // Get all the teeHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("corrosionAllowance.in=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCorrosionAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where corrosionAllowance is not null
        defaultTeeHistShouldBeFound("corrosionAllowance.specified=true");

        // Get all the teeHistList where corrosionAllowance is null
        defaultTeeHistShouldNotBeFound("corrosionAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCorrosionAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where corrosionAllowance greater than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultTeeHistShouldBeFound("corrosionAllowance.greaterOrEqualThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the teeHistList where corrosionAllowance greater than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("corrosionAllowance.greaterOrEqualThan=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCorrosionAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where corrosionAllowance less than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("corrosionAllowance.lessThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the teeHistList where corrosionAllowance less than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultTeeHistShouldBeFound("corrosionAllowance.lessThan=" + UPDATED_CORROSION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByFabricationAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where fabricationAllowance equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultTeeHistShouldBeFound("fabricationAllowance.equals=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the teeHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("fabricationAllowance.equals=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFabricationAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where fabricationAllowance in DEFAULT_FABRICATION_ALLOWANCE or UPDATED_FABRICATION_ALLOWANCE
        defaultTeeHistShouldBeFound("fabricationAllowance.in=" + DEFAULT_FABRICATION_ALLOWANCE + "," + UPDATED_FABRICATION_ALLOWANCE);

        // Get all the teeHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("fabricationAllowance.in=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFabricationAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where fabricationAllowance is not null
        defaultTeeHistShouldBeFound("fabricationAllowance.specified=true");

        // Get all the teeHistList where fabricationAllowance is null
        defaultTeeHistShouldNotBeFound("fabricationAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFabricationAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where fabricationAllowance greater than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultTeeHistShouldBeFound("fabricationAllowance.greaterOrEqualThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the teeHistList where fabricationAllowance greater than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("fabricationAllowance.greaterOrEqualThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFabricationAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where fabricationAllowance less than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultTeeHistShouldNotBeFound("fabricationAllowance.lessThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the teeHistList where fabricationAllowance less than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultTeeHistShouldBeFound("fabricationAllowance.lessThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIsBurialIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isBurial equals to DEFAULT_IS_BURIAL
        defaultTeeHistShouldBeFound("isBurial.equals=" + DEFAULT_IS_BURIAL);

        // Get all the teeHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultTeeHistShouldNotBeFound("isBurial.equals=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsBurialIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isBurial in DEFAULT_IS_BURIAL or UPDATED_IS_BURIAL
        defaultTeeHistShouldBeFound("isBurial.in=" + DEFAULT_IS_BURIAL + "," + UPDATED_IS_BURIAL);

        // Get all the teeHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultTeeHistShouldNotBeFound("isBurial.in=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsBurialIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isBurial is not null
        defaultTeeHistShouldBeFound("isBurial.specified=true");

        // Get all the teeHistList where isBurial is null
        defaultTeeHistShouldNotBeFound("isBurial.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsBurialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isBurial greater than or equals to DEFAULT_IS_BURIAL
        defaultTeeHistShouldBeFound("isBurial.greaterOrEqualThan=" + DEFAULT_IS_BURIAL);

        // Get all the teeHistList where isBurial greater than or equals to UPDATED_IS_BURIAL
        defaultTeeHistShouldNotBeFound("isBurial.greaterOrEqualThan=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsBurialIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isBurial less than or equals to DEFAULT_IS_BURIAL
        defaultTeeHistShouldNotBeFound("isBurial.lessThan=" + DEFAULT_IS_BURIAL);

        // Get all the teeHistList where isBurial less than or equals to UPDATED_IS_BURIAL
        defaultTeeHistShouldBeFound("isBurial.lessThan=" + UPDATED_IS_BURIAL);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where burialDepth equals to DEFAULT_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("burialDepth.equals=" + DEFAULT_BURIAL_DEPTH);

        // Get all the teeHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("burialDepth.equals=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where burialDepth in DEFAULT_BURIAL_DEPTH or UPDATED_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("burialDepth.in=" + DEFAULT_BURIAL_DEPTH + "," + UPDATED_BURIAL_DEPTH);

        // Get all the teeHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("burialDepth.in=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where burialDepth is not null
        defaultTeeHistShouldBeFound("burialDepth.specified=true");

        // Get all the teeHistList where burialDepth is null
        defaultTeeHistShouldNotBeFound("burialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where burialDepth greater than or equals to DEFAULT_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("burialDepth.greaterOrEqualThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the teeHistList where burialDepth greater than or equals to UPDATED_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("burialDepth.greaterOrEqualThan=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where burialDepth less than or equals to DEFAULT_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("burialDepth.lessThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the teeHistList where burialDepth less than or equals to UPDATED_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("burialDepth.lessThan=" + UPDATED_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByFactBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where factBurialDepth equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("factBurialDepth.equals=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the teeHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("factBurialDepth.equals=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFactBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where factBurialDepth in DEFAULT_FACT_BURIAL_DEPTH or UPDATED_FACT_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("factBurialDepth.in=" + DEFAULT_FACT_BURIAL_DEPTH + "," + UPDATED_FACT_BURIAL_DEPTH);

        // Get all the teeHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("factBurialDepth.in=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFactBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where factBurialDepth is not null
        defaultTeeHistShouldBeFound("factBurialDepth.specified=true");

        // Get all the teeHistList where factBurialDepth is null
        defaultTeeHistShouldNotBeFound("factBurialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFactBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where factBurialDepth greater than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("factBurialDepth.greaterOrEqualThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the teeHistList where factBurialDepth greater than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("factBurialDepth.greaterOrEqualThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByFactBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where factBurialDepth less than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultTeeHistShouldNotBeFound("factBurialDepth.lessThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the teeHistList where factBurialDepth less than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultTeeHistShouldBeFound("factBurialDepth.lessThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByDateManufacturedIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateManufactured equals to DEFAULT_DATE_MANUFACTURED
        defaultTeeHistShouldBeFound("dateManufactured.equals=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the teeHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultTeeHistShouldNotBeFound("dateManufactured.equals=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateManufacturedIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateManufactured in DEFAULT_DATE_MANUFACTURED or UPDATED_DATE_MANUFACTURED
        defaultTeeHistShouldBeFound("dateManufactured.in=" + DEFAULT_DATE_MANUFACTURED + "," + UPDATED_DATE_MANUFACTURED);

        // Get all the teeHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultTeeHistShouldNotBeFound("dateManufactured.in=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateManufacturedIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateManufactured is not null
        defaultTeeHistShouldBeFound("dateManufactured.specified=true");

        // Get all the teeHistList where dateManufactured is null
        defaultTeeHistShouldNotBeFound("dateManufactured.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateManufacturedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateManufactured greater than or equals to DEFAULT_DATE_MANUFACTURED
        defaultTeeHistShouldBeFound("dateManufactured.greaterOrEqualThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the teeHistList where dateManufactured greater than or equals to UPDATED_DATE_MANUFACTURED
        defaultTeeHistShouldNotBeFound("dateManufactured.greaterOrEqualThan=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateManufacturedIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateManufactured less than or equals to DEFAULT_DATE_MANUFACTURED
        defaultTeeHistShouldNotBeFound("dateManufactured.lessThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the teeHistList where dateManufactured less than or equals to UPDATED_DATE_MANUFACTURED
        defaultTeeHistShouldBeFound("dateManufactured.lessThan=" + UPDATED_DATE_MANUFACTURED);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByMillTestPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where millTestPressure equals to DEFAULT_MILL_TEST_PRESSURE
        defaultTeeHistShouldBeFound("millTestPressure.equals=" + DEFAULT_MILL_TEST_PRESSURE);

        // Get all the teeHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultTeeHistShouldNotBeFound("millTestPressure.equals=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMillTestPressureIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where millTestPressure in DEFAULT_MILL_TEST_PRESSURE or UPDATED_MILL_TEST_PRESSURE
        defaultTeeHistShouldBeFound("millTestPressure.in=" + DEFAULT_MILL_TEST_PRESSURE + "," + UPDATED_MILL_TEST_PRESSURE);

        // Get all the teeHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultTeeHistShouldNotBeFound("millTestPressure.in=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMillTestPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where millTestPressure is not null
        defaultTeeHistShouldBeFound("millTestPressure.specified=true");

        // Get all the teeHistList where millTestPressure is null
        defaultTeeHistShouldNotBeFound("millTestPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDesignPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where designPressure equals to DEFAULT_DESIGN_PRESSURE
        defaultTeeHistShouldBeFound("designPressure.equals=" + DEFAULT_DESIGN_PRESSURE);

        // Get all the teeHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultTeeHistShouldNotBeFound("designPressure.equals=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDesignPressureIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where designPressure in DEFAULT_DESIGN_PRESSURE or UPDATED_DESIGN_PRESSURE
        defaultTeeHistShouldBeFound("designPressure.in=" + DEFAULT_DESIGN_PRESSURE + "," + UPDATED_DESIGN_PRESSURE);

        // Get all the teeHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultTeeHistShouldNotBeFound("designPressure.in=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDesignPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where designPressure is not null
        defaultTeeHistShouldBeFound("designPressure.specified=true");

        // Get all the teeHistList where designPressure is null
        defaultTeeHistShouldNotBeFound("designPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIncidentalPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where incidentalPressure equals to DEFAULT_INCIDENTAL_PRESSURE
        defaultTeeHistShouldBeFound("incidentalPressure.equals=" + DEFAULT_INCIDENTAL_PRESSURE);

        // Get all the teeHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultTeeHistShouldNotBeFound("incidentalPressure.equals=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIncidentalPressureIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where incidentalPressure in DEFAULT_INCIDENTAL_PRESSURE or UPDATED_INCIDENTAL_PRESSURE
        defaultTeeHistShouldBeFound("incidentalPressure.in=" + DEFAULT_INCIDENTAL_PRESSURE + "," + UPDATED_INCIDENTAL_PRESSURE);

        // Get all the teeHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultTeeHistShouldNotBeFound("incidentalPressure.in=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIncidentalPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where incidentalPressure is not null
        defaultTeeHistShouldBeFound("incidentalPressure.specified=true");

        // Get all the teeHistList where incidentalPressure is null
        defaultTeeHistShouldNotBeFound("incidentalPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateInstalledIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateInstalled equals to DEFAULT_DATE_INSTALLED
        defaultTeeHistShouldBeFound("dateInstalled.equals=" + DEFAULT_DATE_INSTALLED);

        // Get all the teeHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultTeeHistShouldNotBeFound("dateInstalled.equals=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateInstalledIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateInstalled in DEFAULT_DATE_INSTALLED or UPDATED_DATE_INSTALLED
        defaultTeeHistShouldBeFound("dateInstalled.in=" + DEFAULT_DATE_INSTALLED + "," + UPDATED_DATE_INSTALLED);

        // Get all the teeHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultTeeHistShouldNotBeFound("dateInstalled.in=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateInstalledIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateInstalled is not null
        defaultTeeHistShouldBeFound("dateInstalled.specified=true");

        // Get all the teeHistList where dateInstalled is null
        defaultTeeHistShouldNotBeFound("dateInstalled.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateInstalledIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateInstalled greater than or equals to DEFAULT_DATE_INSTALLED
        defaultTeeHistShouldBeFound("dateInstalled.greaterOrEqualThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the teeHistList where dateInstalled greater than or equals to UPDATED_DATE_INSTALLED
        defaultTeeHistShouldNotBeFound("dateInstalled.greaterOrEqualThan=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateInstalledIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateInstalled less than or equals to DEFAULT_DATE_INSTALLED
        defaultTeeHistShouldNotBeFound("dateInstalled.lessThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the teeHistList where dateInstalled less than or equals to UPDATED_DATE_INSTALLED
        defaultTeeHistShouldBeFound("dateInstalled.lessThan=" + UPDATED_DATE_INSTALLED);
    }


    @Test
    @Transactional
    public void getAllTeeHistsBySeamOrientIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seamOrient equals to DEFAULT_SEAM_ORIENT
        defaultTeeHistShouldBeFound("seamOrient.equals=" + DEFAULT_SEAM_ORIENT);

        // Get all the teeHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultTeeHistShouldNotBeFound("seamOrient.equals=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeamOrientIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seamOrient in DEFAULT_SEAM_ORIENT or UPDATED_SEAM_ORIENT
        defaultTeeHistShouldBeFound("seamOrient.in=" + DEFAULT_SEAM_ORIENT + "," + UPDATED_SEAM_ORIENT);

        // Get all the teeHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultTeeHistShouldNotBeFound("seamOrient.in=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeamOrientIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seamOrient is not null
        defaultTeeHistShouldBeFound("seamOrient.specified=true");

        // Get all the teeHistList where seamOrient is null
        defaultTeeHistShouldNotBeFound("seamOrient.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeamAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seamAngle equals to DEFAULT_SEAM_ANGLE
        defaultTeeHistShouldBeFound("seamAngle.equals=" + DEFAULT_SEAM_ANGLE);

        // Get all the teeHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultTeeHistShouldNotBeFound("seamAngle.equals=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeamAngleIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seamAngle in DEFAULT_SEAM_ANGLE or UPDATED_SEAM_ANGLE
        defaultTeeHistShouldBeFound("seamAngle.in=" + DEFAULT_SEAM_ANGLE + "," + UPDATED_SEAM_ANGLE);

        // Get all the teeHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultTeeHistShouldNotBeFound("seamAngle.in=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeamAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seamAngle is not null
        defaultTeeHistShouldBeFound("seamAngle.specified=true");

        // Get all the teeHistList where seamAngle is null
        defaultTeeHistShouldNotBeFound("seamAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByAzimuthIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where azimuth equals to DEFAULT_AZIMUTH
        defaultTeeHistShouldBeFound("azimuth.equals=" + DEFAULT_AZIMUTH);

        // Get all the teeHistList where azimuth equals to UPDATED_AZIMUTH
        defaultTeeHistShouldNotBeFound("azimuth.equals=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByAzimuthIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where azimuth in DEFAULT_AZIMUTH or UPDATED_AZIMUTH
        defaultTeeHistShouldBeFound("azimuth.in=" + DEFAULT_AZIMUTH + "," + UPDATED_AZIMUTH);

        // Get all the teeHistList where azimuth equals to UPDATED_AZIMUTH
        defaultTeeHistShouldNotBeFound("azimuth.in=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByAzimuthIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where azimuth is not null
        defaultTeeHistShouldBeFound("azimuth.specified=true");

        // Get all the teeHistList where azimuth is null
        defaultTeeHistShouldNotBeFound("azimuth.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeabInstallTempIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seabInstallTemp equals to DEFAULT_SEAB_INSTALL_TEMP
        defaultTeeHistShouldBeFound("seabInstallTemp.equals=" + DEFAULT_SEAB_INSTALL_TEMP);

        // Get all the teeHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultTeeHistShouldNotBeFound("seabInstallTemp.equals=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeabInstallTempIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seabInstallTemp in DEFAULT_SEAB_INSTALL_TEMP or UPDATED_SEAB_INSTALL_TEMP
        defaultTeeHistShouldBeFound("seabInstallTemp.in=" + DEFAULT_SEAB_INSTALL_TEMP + "," + UPDATED_SEAB_INSTALL_TEMP);

        // Get all the teeHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultTeeHistShouldNotBeFound("seabInstallTemp.in=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllTeeHistsBySeabInstallTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where seabInstallTemp is not null
        defaultTeeHistShouldBeFound("seabInstallTemp.specified=true");

        // Get all the teeHistList where seabInstallTemp is null
        defaultTeeHistShouldNotBeFound("seabInstallTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByVerticalAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where verticalAngle equals to DEFAULT_VERTICAL_ANGLE
        defaultTeeHistShouldBeFound("verticalAngle.equals=" + DEFAULT_VERTICAL_ANGLE);

        // Get all the teeHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultTeeHistShouldNotBeFound("verticalAngle.equals=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByVerticalAngleIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where verticalAngle in DEFAULT_VERTICAL_ANGLE or UPDATED_VERTICAL_ANGLE
        defaultTeeHistShouldBeFound("verticalAngle.in=" + DEFAULT_VERTICAL_ANGLE + "," + UPDATED_VERTICAL_ANGLE);

        // Get all the teeHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultTeeHistShouldNotBeFound("verticalAngle.in=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByVerticalAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where verticalAngle is not null
        defaultTeeHistShouldBeFound("verticalAngle.specified=true");

        // Get all the teeHistList where verticalAngle is null
        defaultTeeHistShouldNotBeFound("verticalAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByHeatTreatDuratIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where heatTreatDurat equals to DEFAULT_HEAT_TREAT_DURAT
        defaultTeeHistShouldBeFound("heatTreatDurat.equals=" + DEFAULT_HEAT_TREAT_DURAT);

        // Get all the teeHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultTeeHistShouldNotBeFound("heatTreatDurat.equals=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByHeatTreatDuratIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where heatTreatDurat in DEFAULT_HEAT_TREAT_DURAT or UPDATED_HEAT_TREAT_DURAT
        defaultTeeHistShouldBeFound("heatTreatDurat.in=" + DEFAULT_HEAT_TREAT_DURAT + "," + UPDATED_HEAT_TREAT_DURAT);

        // Get all the teeHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultTeeHistShouldNotBeFound("heatTreatDurat.in=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByHeatTreatDuratIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where heatTreatDurat is not null
        defaultTeeHistShouldBeFound("heatTreatDurat.specified=true");

        // Get all the teeHistList where heatTreatDurat is null
        defaultTeeHistShouldNotBeFound("heatTreatDurat.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMaxDesignTempIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where maxDesignTemp equals to DEFAULT_MAX_DESIGN_TEMP
        defaultTeeHistShouldBeFound("maxDesignTemp.equals=" + DEFAULT_MAX_DESIGN_TEMP);

        // Get all the teeHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultTeeHistShouldNotBeFound("maxDesignTemp.equals=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMaxDesignTempIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where maxDesignTemp in DEFAULT_MAX_DESIGN_TEMP or UPDATED_MAX_DESIGN_TEMP
        defaultTeeHistShouldBeFound("maxDesignTemp.in=" + DEFAULT_MAX_DESIGN_TEMP + "," + UPDATED_MAX_DESIGN_TEMP);

        // Get all the teeHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultTeeHistShouldNotBeFound("maxDesignTemp.in=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByMaxDesignTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where maxDesignTemp is not null
        defaultTeeHistShouldBeFound("maxDesignTemp.specified=true");

        // Get all the teeHistList where maxDesignTemp is null
        defaultTeeHistShouldNotBeFound("maxDesignTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByHeatNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where heatNumber equals to DEFAULT_HEAT_NUMBER
        defaultTeeHistShouldBeFound("heatNumber.equals=" + DEFAULT_HEAT_NUMBER);

        // Get all the teeHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultTeeHistShouldNotBeFound("heatNumber.equals=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByHeatNumberIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where heatNumber in DEFAULT_HEAT_NUMBER or UPDATED_HEAT_NUMBER
        defaultTeeHistShouldBeFound("heatNumber.in=" + DEFAULT_HEAT_NUMBER + "," + UPDATED_HEAT_NUMBER);

        // Get all the teeHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultTeeHistShouldNotBeFound("heatNumber.in=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByHeatNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where heatNumber is not null
        defaultTeeHistShouldBeFound("heatNumber.specified=true");

        // Get all the teeHistList where heatNumber is null
        defaultTeeHistShouldNotBeFound("heatNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where coord equals to DEFAULT_COORD
        defaultTeeHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the teeHistList where coord equals to UPDATED_COORD
        defaultTeeHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultTeeHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the teeHistList where coord equals to UPDATED_COORD
        defaultTeeHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where coord is not null
        defaultTeeHistShouldBeFound("coord.specified=true");

        // Get all the teeHistList where coord is null
        defaultTeeHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDesignCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where designCoord equals to DEFAULT_DESIGN_COORD
        defaultTeeHistShouldBeFound("designCoord.equals=" + DEFAULT_DESIGN_COORD);

        // Get all the teeHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultTeeHistShouldNotBeFound("designCoord.equals=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDesignCoordIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where designCoord in DEFAULT_DESIGN_COORD or UPDATED_DESIGN_COORD
        defaultTeeHistShouldBeFound("designCoord.in=" + DEFAULT_DESIGN_COORD + "," + UPDATED_DESIGN_COORD);

        // Get all the teeHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultTeeHistShouldNotBeFound("designCoord.in=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDesignCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where designCoord is not null
        defaultTeeHistShouldBeFound("designCoord.specified=true");

        // Get all the teeHistList where designCoord is null
        defaultTeeHistShouldNotBeFound("designCoord.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByKpInstIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where kpInst equals to DEFAULT_KP_INST
        defaultTeeHistShouldBeFound("kpInst.equals=" + DEFAULT_KP_INST);

        // Get all the teeHistList where kpInst equals to UPDATED_KP_INST
        defaultTeeHistShouldNotBeFound("kpInst.equals=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByKpInstIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where kpInst in DEFAULT_KP_INST or UPDATED_KP_INST
        defaultTeeHistShouldBeFound("kpInst.in=" + DEFAULT_KP_INST + "," + UPDATED_KP_INST);

        // Get all the teeHistList where kpInst equals to UPDATED_KP_INST
        defaultTeeHistShouldNotBeFound("kpInst.in=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByKpInstIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where kpInst is not null
        defaultTeeHistShouldBeFound("kpInst.specified=true");

        // Get all the teeHistList where kpInst is null
        defaultTeeHistShouldNotBeFound("kpInst.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultTeeHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the teeHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultTeeHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultTeeHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the teeHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultTeeHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isCurrentFlag is not null
        defaultTeeHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the teeHistList where isCurrentFlag is null
        defaultTeeHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultTeeHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the teeHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultTeeHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultTeeHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the teeHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultTeeHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllTeeHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where description equals to DEFAULT_DESCRIPTION
        defaultTeeHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the teeHistList where description equals to UPDATED_DESCRIPTION
        defaultTeeHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultTeeHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the teeHistList where description equals to UPDATED_DESCRIPTION
        defaultTeeHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where description is not null
        defaultTeeHistShouldBeFound("description.specified=true");

        // Get all the teeHistList where description is null
        defaultTeeHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where comment equals to DEFAULT_COMMENT
        defaultTeeHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the teeHistList where comment equals to UPDATED_COMMENT
        defaultTeeHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultTeeHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the teeHistList where comment equals to UPDATED_COMMENT
        defaultTeeHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where comment is not null
        defaultTeeHistShouldBeFound("comment.specified=true");

        // Get all the teeHistList where comment is null
        defaultTeeHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultTeeHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the teeHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultTeeHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultTeeHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the teeHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultTeeHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateCreate is not null
        defaultTeeHistShouldBeFound("dateCreate.specified=true");

        // Get all the teeHistList where dateCreate is null
        defaultTeeHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultTeeHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the teeHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultTeeHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultTeeHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the teeHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultTeeHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where dateEdit is not null
        defaultTeeHistShouldBeFound("dateEdit.specified=true");

        // Get all the teeHistList where dateEdit is null
        defaultTeeHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where creator equals to DEFAULT_CREATOR
        defaultTeeHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the teeHistList where creator equals to UPDATED_CREATOR
        defaultTeeHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultTeeHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the teeHistList where creator equals to UPDATED_CREATOR
        defaultTeeHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where creator is not null
        defaultTeeHistShouldBeFound("creator.specified=true");

        // Get all the teeHistList where creator is null
        defaultTeeHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where editor equals to DEFAULT_EDITOR
        defaultTeeHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the teeHistList where editor equals to UPDATED_EDITOR
        defaultTeeHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultTeeHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the teeHistList where editor equals to UPDATED_EDITOR
        defaultTeeHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllTeeHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        // Get all the teeHistList where editor is not null
        defaultTeeHistShouldBeFound("editor.specified=true");

        // Get all the teeHistList where editor is null
        defaultTeeHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllTeeHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Tee id = teeHist.getId();
        teeHistRepository.saveAndFlush(teeHist);
        Long idId = id.getId();

        // Get all the teeHistList where id equals to idId
        defaultTeeHistShouldBeFound("idId.equals=" + idId);

        // Get all the teeHistList where id equals to idId + 1
        defaultTeeHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = teeHist.getIdPipelineSection();
        teeHistRepository.saveAndFlush(teeHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the teeHistList where idPipelineSection equals to idPipelineSectionId
        defaultTeeHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the teeHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultTeeHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListTeeType idType = ListTeeTypeResourceIT.createEntity(em);
        em.persist(idType);
        em.flush();
        teeHist.setIdType(idType);
        teeHistRepository.saveAndFlush(teeHist);
        Long idTypeId = idType.getId();

        // Get all the teeHistList where idType equals to idTypeId
        defaultTeeHistShouldBeFound("idTypeId.equals=" + idTypeId);

        // Get all the teeHistList where idType equals to idTypeId + 1
        defaultTeeHistShouldNotBeFound("idTypeId.equals=" + (idTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdInternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListInternalCoating idInternalCoatType = ListInternalCoatingResourceIT.createEntity(em);
        em.persist(idInternalCoatType);
        em.flush();
        teeHist.setIdInternalCoatType(idInternalCoatType);
        teeHistRepository.saveAndFlush(teeHist);
        Long idInternalCoatTypeId = idInternalCoatType.getId();

        // Get all the teeHistList where idInternalCoatType equals to idInternalCoatTypeId
        defaultTeeHistShouldBeFound("idInternalCoatTypeId.equals=" + idInternalCoatTypeId);

        // Get all the teeHistList where idInternalCoatType equals to idInternalCoatTypeId + 1
        defaultTeeHistShouldNotBeFound("idInternalCoatTypeId.equals=" + (idInternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdExternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListExternalCoating idExternalCoatType = ListExternalCoatingResourceIT.createEntity(em);
        em.persist(idExternalCoatType);
        em.flush();
        teeHist.setIdExternalCoatType(idExternalCoatType);
        teeHistRepository.saveAndFlush(teeHist);
        Long idExternalCoatTypeId = idExternalCoatType.getId();

        // Get all the teeHistList where idExternalCoatType equals to idExternalCoatTypeId
        defaultTeeHistShouldBeFound("idExternalCoatTypeId.equals=" + idExternalCoatTypeId);

        // Get all the teeHistList where idExternalCoatType equals to idExternalCoatTypeId + 1
        defaultTeeHistShouldNotBeFound("idExternalCoatTypeId.equals=" + (idExternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdNominalWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        ListNominalWallThickness idNominalWallThickness = ListNominalWallThicknessResourceIT.createEntity(em);
        em.persist(idNominalWallThickness);
        em.flush();
        teeHist.setIdNominalWallThickness(idNominalWallThickness);
        teeHistRepository.saveAndFlush(teeHist);
        Long idNominalWallThicknessId = idNominalWallThickness.getId();

        // Get all the teeHistList where idNominalWallThickness equals to idNominalWallThicknessId
        defaultTeeHistShouldBeFound("idNominalWallThicknessId.equals=" + idNominalWallThicknessId);

        // Get all the teeHistList where idNominalWallThickness equals to idNominalWallThicknessId + 1
        defaultTeeHistShouldNotBeFound("idNominalWallThicknessId.equals=" + (idNominalWallThicknessId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdPipeJointIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipejoint idPipeJoint = PipejointResourceIT.createEntity(em);
        em.persist(idPipeJoint);
        em.flush();
        teeHist.setIdPipeJoint(idPipeJoint);
        teeHistRepository.saveAndFlush(teeHist);
        Long idPipeJointId = idPipeJoint.getId();

        // Get all the teeHistList where idPipeJoint equals to idPipeJointId
        defaultTeeHistShouldBeFound("idPipeJointId.equals=" + idPipeJointId);

        // Get all the teeHistList where idPipeJoint equals to idPipeJointId + 1
        defaultTeeHistShouldNotBeFound("idPipeJointId.equals=" + (idPipeJointId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdManufacturerIsEqualToSomething() throws Exception {
        // Initialize the database
        ListTeeManufacturer idManufacturer = ListTeeManufacturerResourceIT.createEntity(em);
        em.persist(idManufacturer);
        em.flush();
        teeHist.setIdManufacturer(idManufacturer);
        teeHistRepository.saveAndFlush(teeHist);
        Long idManufacturerId = idManufacturer.getId();

        // Get all the teeHistList where idManufacturer equals to idManufacturerId
        defaultTeeHistShouldBeFound("idManufacturerId.equals=" + idManufacturerId);

        // Get all the teeHistList where idManufacturer equals to idManufacturerId + 1
        defaultTeeHistShouldNotBeFound("idManufacturerId.equals=" + (idManufacturerId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdSpecificationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListTeeSpecification idSpecification = ListTeeSpecificationResourceIT.createEntity(em);
        em.persist(idSpecification);
        em.flush();
        teeHist.setIdSpecification(idSpecification);
        teeHistRepository.saveAndFlush(teeHist);
        Long idSpecificationId = idSpecification.getId();

        // Get all the teeHistList where idSpecification equals to idSpecificationId
        defaultTeeHistShouldBeFound("idSpecificationId.equals=" + idSpecificationId);

        // Get all the teeHistList where idSpecification equals to idSpecificationId + 1
        defaultTeeHistShouldNotBeFound("idSpecificationId.equals=" + (idSpecificationId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdLongSeamWeldTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListLongSeamWeldType idLongSeamWeldType = ListLongSeamWeldTypeResourceIT.createEntity(em);
        em.persist(idLongSeamWeldType);
        em.flush();
        teeHist.setIdLongSeamWeldType(idLongSeamWeldType);
        teeHistRepository.saveAndFlush(teeHist);
        Long idLongSeamWeldTypeId = idLongSeamWeldType.getId();

        // Get all the teeHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId
        defaultTeeHistShouldBeFound("idLongSeamWeldTypeId.equals=" + idLongSeamWeldTypeId);

        // Get all the teeHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId + 1
        defaultTeeHistShouldNotBeFound("idLongSeamWeldTypeId.equals=" + (idLongSeamWeldTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdFabricationTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListFabricationType idFabricationType = ListFabricationTypeResourceIT.createEntity(em);
        em.persist(idFabricationType);
        em.flush();
        teeHist.setIdFabricationType(idFabricationType);
        teeHistRepository.saveAndFlush(teeHist);
        Long idFabricationTypeId = idFabricationType.getId();

        // Get all the teeHistList where idFabricationType equals to idFabricationTypeId
        defaultTeeHistShouldBeFound("idFabricationTypeId.equals=" + idFabricationTypeId);

        // Get all the teeHistList where idFabricationType equals to idFabricationTypeId + 1
        defaultTeeHistShouldNotBeFound("idFabricationTypeId.equals=" + (idFabricationTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMaterial idMaterial = ListMaterialResourceIT.createEntity(em);
        em.persist(idMaterial);
        em.flush();
        teeHist.setIdMaterial(idMaterial);
        teeHistRepository.saveAndFlush(teeHist);
        Long idMaterialId = idMaterial.getId();

        // Get all the teeHistList where idMaterial equals to idMaterialId
        defaultTeeHistShouldBeFound("idMaterialId.equals=" + idMaterialId);

        // Get all the teeHistList where idMaterial equals to idMaterialId + 1
        defaultTeeHistShouldNotBeFound("idMaterialId.equals=" + (idMaterialId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdMillLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMillLocation idMillLocation = ListMillLocationResourceIT.createEntity(em);
        em.persist(idMillLocation);
        em.flush();
        teeHist.setIdMillLocation(idMillLocation);
        teeHistRepository.saveAndFlush(teeHist);
        Long idMillLocationId = idMillLocation.getId();

        // Get all the teeHistList where idMillLocation equals to idMillLocationId
        defaultTeeHistShouldBeFound("idMillLocationId.equals=" + idMillLocationId);

        // Get all the teeHistList where idMillLocation equals to idMillLocationId + 1
        defaultTeeHistShouldNotBeFound("idMillLocationId.equals=" + (idMillLocationId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdSteelGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListSteelGrade idSteelGrade = ListSteelGradeResourceIT.createEntity(em);
        em.persist(idSteelGrade);
        em.flush();
        teeHist.setIdSteelGrade(idSteelGrade);
        teeHistRepository.saveAndFlush(teeHist);
        Long idSteelGradeId = idSteelGrade.getId();

        // Get all the teeHistList where idSteelGrade equals to idSteelGradeId
        defaultTeeHistShouldBeFound("idSteelGradeId.equals=" + idSteelGradeId);

        // Get all the teeHistList where idSteelGrade equals to idSteelGradeId + 1
        defaultTeeHistShouldNotBeFound("idSteelGradeId.equals=" + (idSteelGradeId + 1));
    }


    @Test
    @Transactional
    public void getAllTeeHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = teeHist.getIdStatus();
        teeHistRepository.saveAndFlush(teeHist);
        Long idStatusId = idStatus.getId();

        // Get all the teeHistList where idStatus equals to idStatusId
        defaultTeeHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the teeHistList where idStatus equals to idStatusId + 1
        defaultTeeHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTeeHistShouldBeFound(String filter) throws Exception {
        restTeeHistMockMvc.perform(get("/api/tee-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teeHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].diameterOuterSteel").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL)))
            .andExpect(jsonPath("$.[*].diameterOuterSteelBr").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL_BR)))
            .andExpect(jsonPath("$.[*].internalCoatThickness").value(hasItem(DEFAULT_INTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].isConcrCoating").value(hasItem(DEFAULT_IS_CONCR_COATING)))
            .andExpect(jsonPath("$.[*].concrCoatThickness").value(hasItem(DEFAULT_CONCR_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].concrCoatDensity").value(hasItem(DEFAULT_CONCR_COAT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].measWallThickness").value(hasItem(DEFAULT_MEAS_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].smts").value(hasItem(DEFAULT_SMTS.intValue())))
            .andExpect(jsonPath("$.[*].smys").value(hasItem(DEFAULT_SMYS.intValue())))
            .andExpect(jsonPath("$.[*].sdbm").value(hasItem(DEFAULT_SDBM.intValue())))
            .andExpect(jsonPath("$.[*].sdaf").value(hasItem(DEFAULT_SDAF.intValue())))
            .andExpect(jsonPath("$.[*].sdcs").value(hasItem(DEFAULT_SDCS.intValue())))
            .andExpect(jsonPath("$.[*].allowTensStrain").value(hasItem(DEFAULT_ALLOW_TENS_STRAIN.intValue())))
            .andExpect(jsonPath("$.[*].corrosionAllowance").value(hasItem(DEFAULT_CORROSION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].fabricationAllowance").value(hasItem(DEFAULT_FABRICATION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].burialDepth").value(hasItem(DEFAULT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].factBurialDepth").value(hasItem(DEFAULT_FACT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].incidentalPressure").value(hasItem(DEFAULT_INCIDENTAL_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].dateInstalled").value(hasItem(DEFAULT_DATE_INSTALLED.toString())))
            .andExpect(jsonPath("$.[*].seamOrient").value(hasItem(DEFAULT_SEAM_ORIENT.intValue())))
            .andExpect(jsonPath("$.[*].seamAngle").value(hasItem(DEFAULT_SEAM_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].azimuth").value(hasItem(DEFAULT_AZIMUTH.intValue())))
            .andExpect(jsonPath("$.[*].seabInstallTemp").value(hasItem(DEFAULT_SEAB_INSTALL_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].verticalAngle").value(hasItem(DEFAULT_VERTICAL_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].heatTreatDurat").value(hasItem(DEFAULT_HEAT_TREAT_DURAT.intValue())))
            .andExpect(jsonPath("$.[*].maxDesignTemp").value(hasItem(DEFAULT_MAX_DESIGN_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].heatNumber").value(hasItem(DEFAULT_HEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD)))
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restTeeHistMockMvc.perform(get("/api/tee-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTeeHistShouldNotBeFound(String filter) throws Exception {
        restTeeHistMockMvc.perform(get("/api/tee-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTeeHistMockMvc.perform(get("/api/tee-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTeeHist() throws Exception {
        // Get the teeHist
        restTeeHistMockMvc.perform(get("/api/tee-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTeeHist() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        int databaseSizeBeforeUpdate = teeHistRepository.findAll().size();

        // Update the teeHist
        TeeHist updatedTeeHist = teeHistRepository.findById(teeHist.getId()).get();
        // Disconnect from session so that the updates on updatedTeeHist are not directly saved in db
        em.detach(updatedTeeHist);
        updatedTeeHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .name(UPDATED_NAME)
            .diameterOuterSteel(UPDATED_DIAMETER_OUTER_STEEL)
            .diameterOuterSteelBr(UPDATED_DIAMETER_OUTER_STEEL_BR)
            .internalCoatThickness(UPDATED_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(UPDATED_IS_CONCR_COATING)
            .concrCoatThickness(UPDATED_CONCR_COAT_THICKNESS)
            .concrCoatDensity(UPDATED_CONCR_COAT_DENSITY)
            .measWallThickness(UPDATED_MEAS_WALL_THICKNESS)
            .length(UPDATED_LENGTH)
            .weight(UPDATED_WEIGHT)
            .smts(UPDATED_SMTS)
            .smys(UPDATED_SMYS)
            .sdbm(UPDATED_SDBM)
            .sdaf(UPDATED_SDAF)
            .sdcs(UPDATED_SDCS)
            .allowTensStrain(UPDATED_ALLOW_TENS_STRAIN)
            .corrosionAllowance(UPDATED_CORROSION_ALLOWANCE)
            .fabricationAllowance(UPDATED_FABRICATION_ALLOWANCE)
            .isBurial(UPDATED_IS_BURIAL)
            .burialDepth(UPDATED_BURIAL_DEPTH)
            .factBurialDepth(UPDATED_FACT_BURIAL_DEPTH)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
            .incidentalPressure(UPDATED_INCIDENTAL_PRESSURE)
            .dateInstalled(UPDATED_DATE_INSTALLED)
            .seamOrient(UPDATED_SEAM_ORIENT)
            .seamAngle(UPDATED_SEAM_ANGLE)
            .azimuth(UPDATED_AZIMUTH)
            .seabInstallTemp(UPDATED_SEAB_INSTALL_TEMP)
            .verticalAngle(UPDATED_VERTICAL_ANGLE)
            .heatTreatDurat(UPDATED_HEAT_TREAT_DURAT)
            .maxDesignTemp(UPDATED_MAX_DESIGN_TEMP)
            .heatNumber(UPDATED_HEAT_NUMBER)
            .coord(UPDATED_COORD)
            .designCoord(UPDATED_DESIGN_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        TeeHistDTO teeHistDTO = teeHistMapper.toDto(updatedTeeHist);

        restTeeHistMockMvc.perform(put("/api/tee-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeHistDTO)))
            .andExpect(status().isOk());

        // Validate the TeeHist in the database
        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeUpdate);
        TeeHist testTeeHist = teeHistList.get(teeHistList.size() - 1);
        assertThat(testTeeHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testTeeHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testTeeHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testTeeHist.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTeeHist.getDiameterOuterSteel()).isEqualTo(UPDATED_DIAMETER_OUTER_STEEL);
        assertThat(testTeeHist.getDiameterOuterSteelBr()).isEqualTo(UPDATED_DIAMETER_OUTER_STEEL_BR);
        assertThat(testTeeHist.getInternalCoatThickness()).isEqualTo(UPDATED_INTERNAL_COAT_THICKNESS);
        assertThat(testTeeHist.getExternalCoatThickness()).isEqualTo(UPDATED_EXTERNAL_COAT_THICKNESS);
        assertThat(testTeeHist.getIsConcrCoating()).isEqualTo(UPDATED_IS_CONCR_COATING);
        assertThat(testTeeHist.getConcrCoatThickness()).isEqualTo(UPDATED_CONCR_COAT_THICKNESS);
        assertThat(testTeeHist.getConcrCoatDensity()).isEqualTo(UPDATED_CONCR_COAT_DENSITY);
        assertThat(testTeeHist.getMeasWallThickness()).isEqualTo(UPDATED_MEAS_WALL_THICKNESS);
        assertThat(testTeeHist.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testTeeHist.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testTeeHist.getSmts()).isEqualTo(UPDATED_SMTS);
        assertThat(testTeeHist.getSmys()).isEqualTo(UPDATED_SMYS);
        assertThat(testTeeHist.getSdbm()).isEqualTo(UPDATED_SDBM);
        assertThat(testTeeHist.getSdaf()).isEqualTo(UPDATED_SDAF);
        assertThat(testTeeHist.getSdcs()).isEqualTo(UPDATED_SDCS);
        assertThat(testTeeHist.getAllowTensStrain()).isEqualTo(UPDATED_ALLOW_TENS_STRAIN);
        assertThat(testTeeHist.getCorrosionAllowance()).isEqualTo(UPDATED_CORROSION_ALLOWANCE);
        assertThat(testTeeHist.getFabricationAllowance()).isEqualTo(UPDATED_FABRICATION_ALLOWANCE);
        assertThat(testTeeHist.getIsBurial()).isEqualTo(UPDATED_IS_BURIAL);
        assertThat(testTeeHist.getBurialDepth()).isEqualTo(UPDATED_BURIAL_DEPTH);
        assertThat(testTeeHist.getFactBurialDepth()).isEqualTo(UPDATED_FACT_BURIAL_DEPTH);
        assertThat(testTeeHist.getDateManufactured()).isEqualTo(UPDATED_DATE_MANUFACTURED);
        assertThat(testTeeHist.getMillTestPressure()).isEqualTo(UPDATED_MILL_TEST_PRESSURE);
        assertThat(testTeeHist.getDesignPressure()).isEqualTo(UPDATED_DESIGN_PRESSURE);
        assertThat(testTeeHist.getIncidentalPressure()).isEqualTo(UPDATED_INCIDENTAL_PRESSURE);
        assertThat(testTeeHist.getDateInstalled()).isEqualTo(UPDATED_DATE_INSTALLED);
        assertThat(testTeeHist.getSeamOrient()).isEqualTo(UPDATED_SEAM_ORIENT);
        assertThat(testTeeHist.getSeamAngle()).isEqualTo(UPDATED_SEAM_ANGLE);
        assertThat(testTeeHist.getAzimuth()).isEqualTo(UPDATED_AZIMUTH);
        assertThat(testTeeHist.getSeabInstallTemp()).isEqualTo(UPDATED_SEAB_INSTALL_TEMP);
        assertThat(testTeeHist.getVerticalAngle()).isEqualTo(UPDATED_VERTICAL_ANGLE);
        assertThat(testTeeHist.getHeatTreatDurat()).isEqualTo(UPDATED_HEAT_TREAT_DURAT);
        assertThat(testTeeHist.getMaxDesignTemp()).isEqualTo(UPDATED_MAX_DESIGN_TEMP);
        assertThat(testTeeHist.getHeatNumber()).isEqualTo(UPDATED_HEAT_NUMBER);
        assertThat(testTeeHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testTeeHist.getDesignCoord()).isEqualTo(UPDATED_DESIGN_COORD);
        assertThat(testTeeHist.getKpInst()).isEqualTo(UPDATED_KP_INST);
        assertThat(testTeeHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testTeeHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTeeHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testTeeHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testTeeHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testTeeHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testTeeHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingTeeHist() throws Exception {
        int databaseSizeBeforeUpdate = teeHistRepository.findAll().size();

        // Create the TeeHist
        TeeHistDTO teeHistDTO = teeHistMapper.toDto(teeHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeeHistMockMvc.perform(put("/api/tee-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teeHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TeeHist in the database
        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTeeHist() throws Exception {
        // Initialize the database
        teeHistRepository.saveAndFlush(teeHist);

        int databaseSizeBeforeDelete = teeHistRepository.findAll().size();

        // Delete the teeHist
        restTeeHistMockMvc.perform(delete("/api/tee-hists/{id}", teeHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<TeeHist> teeHistList = teeHistRepository.findAll();
        assertThat(teeHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeeHist.class);
        TeeHist teeHist1 = new TeeHist();
        teeHist1.setId(1L);
        TeeHist teeHist2 = new TeeHist();
        teeHist2.setId(teeHist1.getId());
        assertThat(teeHist1).isEqualTo(teeHist2);
        teeHist2.setId(2L);
        assertThat(teeHist1).isNotEqualTo(teeHist2);
        teeHist1.setId(null);
        assertThat(teeHist1).isNotEqualTo(teeHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeeHistDTO.class);
        TeeHistDTO teeHistDTO1 = new TeeHistDTO();
        teeHistDTO1.setId(1L);
        TeeHistDTO teeHistDTO2 = new TeeHistDTO();
        assertThat(teeHistDTO1).isNotEqualTo(teeHistDTO2);
        teeHistDTO2.setId(teeHistDTO1.getId());
        assertThat(teeHistDTO1).isEqualTo(teeHistDTO2);
        teeHistDTO2.setId(2L);
        assertThat(teeHistDTO1).isNotEqualTo(teeHistDTO2);
        teeHistDTO1.setId(null);
        assertThat(teeHistDTO1).isNotEqualTo(teeHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(teeHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(teeHistMapper.fromId(null)).isNull();
    }
}
