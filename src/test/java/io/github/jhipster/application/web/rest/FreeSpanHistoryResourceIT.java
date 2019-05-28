package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FreeSpanHistory;
import io.github.jhipster.application.repository.FreeSpanHistoryRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link FreeSpanHistoryResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FreeSpanHistoryResourceIT {

    private static final Instant DEFAULT_DATE_FORM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_FORM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_TO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_TO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_WORK_ID = 1L;
    private static final Long UPDATED_WORK_ID = 2L;

    private static final Double DEFAULT_LENGTH = 1D;
    private static final Double UPDATED_LENGTH = 2D;

    private static final Double DEFAULT_KP_START = 1D;
    private static final Double UPDATED_KP_START = 2D;

    private static final Double DEFAULT_KP_END = 1D;
    private static final Double UPDATED_KP_END = 2D;

    private static final Boolean DEFAULT_IS_CURRENT_FLAG = false;
    private static final Boolean UPDATED_IS_CURRENT_FLAG = true;

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
    private FreeSpanHistoryRepository freeSpanHistoryRepository;

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

    private MockMvc restFreeSpanHistoryMockMvc;

    private FreeSpanHistory freeSpanHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FreeSpanHistoryResource freeSpanHistoryResource = new FreeSpanHistoryResource(freeSpanHistoryRepository);
        this.restFreeSpanHistoryMockMvc = MockMvcBuilders.standaloneSetup(freeSpanHistoryResource)
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
    public static FreeSpanHistory createEntity(EntityManager em) {
        FreeSpanHistory freeSpanHistory = new FreeSpanHistory()
            .dateForm(DEFAULT_DATE_FORM)
            .dateTo(DEFAULT_DATE_TO)
            .workId(DEFAULT_WORK_ID)
            .length(DEFAULT_LENGTH)
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return freeSpanHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FreeSpanHistory createUpdatedEntity(EntityManager em) {
        FreeSpanHistory freeSpanHistory = new FreeSpanHistory()
            .dateForm(UPDATED_DATE_FORM)
            .dateTo(UPDATED_DATE_TO)
            .workId(UPDATED_WORK_ID)
            .length(UPDATED_LENGTH)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return freeSpanHistory;
    }

    @BeforeEach
    public void initTest() {
        freeSpanHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreeSpanHistory() throws Exception {
        int databaseSizeBeforeCreate = freeSpanHistoryRepository.findAll().size();

        // Create the FreeSpanHistory
        restFreeSpanHistoryMockMvc.perform(post("/api/free-span-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistory)))
            .andExpect(status().isCreated());

        // Validate the FreeSpanHistory in the database
        List<FreeSpanHistory> freeSpanHistoryList = freeSpanHistoryRepository.findAll();
        assertThat(freeSpanHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        FreeSpanHistory testFreeSpanHistory = freeSpanHistoryList.get(freeSpanHistoryList.size() - 1);
        assertThat(testFreeSpanHistory.getDateForm()).isEqualTo(DEFAULT_DATE_FORM);
        assertThat(testFreeSpanHistory.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testFreeSpanHistory.getWorkId()).isEqualTo(DEFAULT_WORK_ID);
        assertThat(testFreeSpanHistory.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testFreeSpanHistory.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testFreeSpanHistory.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testFreeSpanHistory.isIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testFreeSpanHistory.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testFreeSpanHistory.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testFreeSpanHistory.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testFreeSpanHistory.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testFreeSpanHistory.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createFreeSpanHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freeSpanHistoryRepository.findAll().size();

        // Create the FreeSpanHistory with an existing ID
        freeSpanHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreeSpanHistoryMockMvc.perform(post("/api/free-span-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistory)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanHistory in the database
        List<FreeSpanHistory> freeSpanHistoryList = freeSpanHistoryRepository.findAll();
        assertThat(freeSpanHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistories() throws Exception {
        // Initialize the database
        freeSpanHistoryRepository.saveAndFlush(freeSpanHistory);

        // Get all the freeSpanHistoryList
        restFreeSpanHistoryMockMvc.perform(get("/api/free-span-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpanHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateForm").value(hasItem(DEFAULT_DATE_FORM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].workId").value(hasItem(DEFAULT_WORK_ID.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.doubleValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.doubleValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getFreeSpanHistory() throws Exception {
        // Initialize the database
        freeSpanHistoryRepository.saveAndFlush(freeSpanHistory);

        // Get the freeSpanHistory
        restFreeSpanHistoryMockMvc.perform(get("/api/free-span-histories/{id}", freeSpanHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freeSpanHistory.getId().intValue()))
            .andExpect(jsonPath("$.dateForm").value(DEFAULT_DATE_FORM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.workId").value(DEFAULT_WORK_ID.intValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.doubleValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.doubleValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG.booleanValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFreeSpanHistory() throws Exception {
        // Get the freeSpanHistory
        restFreeSpanHistoryMockMvc.perform(get("/api/free-span-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreeSpanHistory() throws Exception {
        // Initialize the database
        freeSpanHistoryRepository.saveAndFlush(freeSpanHistory);

        int databaseSizeBeforeUpdate = freeSpanHistoryRepository.findAll().size();

        // Update the freeSpanHistory
        FreeSpanHistory updatedFreeSpanHistory = freeSpanHistoryRepository.findById(freeSpanHistory.getId()).get();
        // Disconnect from session so that the updates on updatedFreeSpanHistory are not directly saved in db
        em.detach(updatedFreeSpanHistory);
        updatedFreeSpanHistory
            .dateForm(UPDATED_DATE_FORM)
            .dateTo(UPDATED_DATE_TO)
            .workId(UPDATED_WORK_ID)
            .length(UPDATED_LENGTH)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);

        restFreeSpanHistoryMockMvc.perform(put("/api/free-span-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFreeSpanHistory)))
            .andExpect(status().isOk());

        // Validate the FreeSpanHistory in the database
        List<FreeSpanHistory> freeSpanHistoryList = freeSpanHistoryRepository.findAll();
        assertThat(freeSpanHistoryList).hasSize(databaseSizeBeforeUpdate);
        FreeSpanHistory testFreeSpanHistory = freeSpanHistoryList.get(freeSpanHistoryList.size() - 1);
        assertThat(testFreeSpanHistory.getDateForm()).isEqualTo(UPDATED_DATE_FORM);
        assertThat(testFreeSpanHistory.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testFreeSpanHistory.getWorkId()).isEqualTo(UPDATED_WORK_ID);
        assertThat(testFreeSpanHistory.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testFreeSpanHistory.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testFreeSpanHistory.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testFreeSpanHistory.isIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testFreeSpanHistory.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testFreeSpanHistory.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testFreeSpanHistory.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testFreeSpanHistory.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testFreeSpanHistory.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingFreeSpanHistory() throws Exception {
        int databaseSizeBeforeUpdate = freeSpanHistoryRepository.findAll().size();

        // Create the FreeSpanHistory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreeSpanHistoryMockMvc.perform(put("/api/free-span-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistory)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanHistory in the database
        List<FreeSpanHistory> freeSpanHistoryList = freeSpanHistoryRepository.findAll();
        assertThat(freeSpanHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFreeSpanHistory() throws Exception {
        // Initialize the database
        freeSpanHistoryRepository.saveAndFlush(freeSpanHistory);

        int databaseSizeBeforeDelete = freeSpanHistoryRepository.findAll().size();

        // Delete the freeSpanHistory
        restFreeSpanHistoryMockMvc.perform(delete("/api/free-span-histories/{id}", freeSpanHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FreeSpanHistory> freeSpanHistoryList = freeSpanHistoryRepository.findAll();
        assertThat(freeSpanHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanHistory.class);
        FreeSpanHistory freeSpanHistory1 = new FreeSpanHistory();
        freeSpanHistory1.setId(1L);
        FreeSpanHistory freeSpanHistory2 = new FreeSpanHistory();
        freeSpanHistory2.setId(freeSpanHistory1.getId());
        assertThat(freeSpanHistory1).isEqualTo(freeSpanHistory2);
        freeSpanHistory2.setId(2L);
        assertThat(freeSpanHistory1).isNotEqualTo(freeSpanHistory2);
        freeSpanHistory1.setId(null);
        assertThat(freeSpanHistory1).isNotEqualTo(freeSpanHistory2);
    }
}
