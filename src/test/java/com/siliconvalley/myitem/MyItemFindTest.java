package com.siliconvalley.myitem;
import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.item.myitem.dao.MyItemRepository;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.item.myitem.exception.MyItemNotFoundException;
import com.siliconvalley.global.common.dto.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyItemFindTest {

    MyItemFindDao myItemFindDao;

    @Mock
    MyItemRepository myItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        myItemFindDao = new MyItemFindDao(myItemRepository);
    }

    @Test
    @DisplayName("MyItem 조회 테스트")
    public void testFindById() {
        // Given
        Long myItemId = 1L;
        MyItem myItem = new MyItem(); // 적절한 MyItem 객체 생성 및 설정

        when(myItemRepository.findById(myItemId)).thenReturn(Optional.of(myItem));

        // When
        MyItem result = myItemFindDao.findById(myItemId);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("존재하지 않는 MyItem 조회 시 예외 발생 테스트")
    public void testFindByIdNotFound() {
        // Given
        Long myItemId = 1L;

        when(myItemRepository.findById(myItemId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(MyItemNotFoundException.class, () -> myItemFindDao.findById(myItemId));
    }

    @Test
    @DisplayName("MyItem 목록 조회 테스트")
    public void testGetMyItemListByPage() {
        // Given
        Long profileId = 1L;
        Pageable pageable = mock(Pageable.class);
        String category = "avatar";
        Page<MyItem> myItemPage = mock(Page.class);
        Page<Object> myItemResponsePage = mock(Page.class);
        List<MyItem> myItemList = new ArrayList<>(); // 적절한 MyItem 목록 생성 및 설정

        when(myItemRepository.findByProfileIdAndItemType(profileId, category, pageable)).thenReturn(myItemPage);
        when(myItemPage.map(any())).thenReturn(myItemResponsePage);
        when(myItemPage.getContent()).thenReturn(myItemList);

        // When
        Response response = myItemFindDao.getMyItemListByPage(profileId, pageable, category);

        // Then
        assertNotNull(response);
    }
}

