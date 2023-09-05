package com.javatunes.catalog;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class InMemoryCatalogTest {
    // object under test - "fixture"
    private InMemoryCatalog catalog;

    // before method
    @Before
    public void setUp(){
        catalog = new InMemoryCatalog();
    }

    // test methods


    @Test
    public void testFindCheapRock_shouldReturnList_lessThanSetPrice() {

    }

    @Test
    public void findSelfTitled_shouldReturnSelfTitled_whenArtistMatches() {
        Collection<MusicItem> dupes = catalog.getSelfTitled();
        assertEquals(2, dupes.size());

    }

    @Test
    public void findByCategory_shouldReturnPopulatedCollection_whenGenreFound() {
        Collection<MusicItem> popItems = catalog.findByCategory(MusicCategory.POP);
        // should be size 4
        // all MusicItems in this collection should have a musicCategory of POP
        assertEquals(4, popItems.size());

    }

    @Test
    public void findById_shouldReturnNull_idNotFound() {
        MusicItem item = catalog.findById(19L);
        assertNull(item);
    }

    @Test
    public void findById_shouldReturnMusicItemWithThatId_whenIdFound() {
        MusicItem item = catalog.findById(6L);  // need the L to make it a long not an int
        assertEquals(6L,item.getId().longValue());
    }
}