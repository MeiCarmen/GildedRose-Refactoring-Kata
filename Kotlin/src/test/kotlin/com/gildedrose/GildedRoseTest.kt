package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    ///////////////// New features Tests
    /*
    @Test
    fun conjuredQualityDecreaseBeforeSellIn() {
        val items = arrayOf<Item>(Item("Conjured Mana Cake", 1, 6))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.items[0].quality)
    }
    @Test
    fun conjuredQualityDecreaseAtSellIn() {
        val items = arrayOf<Item>(Item("Conjured Mana Cake", 0, 6))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.items[0].quality)
    }
    @Test
    fun conjuredQualityNeverNegative() {
        val items = arrayOf<Item>(Item("Conjured Mana Cake", -1, 3))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }*/

    ///////////////// Old features Tests
    @Test
    fun qualityDecreaseBeforeSellIn() {
        val items = arrayOf<Item>(Item("foo", 1, 6))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(5, app.items[0].quality)
    }
    //Once the sell by date has passed, Quality degrades twice as fast
    @Test
    fun qualityDecreaseAtSellIn() {
        val items = arrayOf<Item>(Item("foo", 0, 6))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.items[0].quality)
    }
    @Test
    fun qualityDecreaseAfterSellIn() {
        val items = arrayOf<Item>(Item("foo", -2, 6))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, app.items[0].quality)
    }
    //The Quality of an item is never negative
    @Test
    fun qualityNotNegativeFrom0() {
        val items = arrayOf<Item>(Item("foo", 5, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }
    @Test
    fun qualityNotNegativeFrom1() {
        val items = arrayOf<Item>(Item("foo", -5, 1))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }
    //"Aged Brie" actually increases in Quality the older it gets
    @Test
    fun agedBrieQualityIncrease() {
        val items = arrayOf<Item>(Item("Aged Brie", 5, 1))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.items[0].quality)
    }
    @Test
    fun agedBrieQualityIncreaseAfterSellIn() {
        val items = arrayOf<Item>(Item("Aged Brie", -5, 1))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(3, app.items[0].quality)
    }
    //The Quality of an item is never more than 50
    @Test
    fun agedBrieQualityMax50() {
        val items = arrayOf<Item>(Item("Aged Brie", 5, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }
    @Test
    fun BackstageQualityMax50() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 1, 49))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }
    //"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    @Test
    fun sulfurasConstantPositiveSellIn() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 5, 25))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(25, app.items[0].quality)
    }
    @Test
    fun sulfurasConstantNegativeSellIn() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", -5, 25))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(25, app.items[0].quality)
    }
    @Test
    fun sulfurasConstantAtSellIn() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 0, 25))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(25, app.items[0].quality)
    }
    @Test
    fun sulfurasConstantQualityOverMax() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 4, 255))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(255, app.items[0].quality)
    }
    @Test
    fun sulfurasConstantQualityUnderMin() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 4, -255))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(-255, app.items[0].quality)
    }
    //"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    @Test
    fun BackstageSellInOver10() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 12, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(5, app.items[0].quality)
    }
    @Test
    fun BackstageSellIn6To10() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 10, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.items[0].quality)
    }
    @Test
    fun BackstageSellIn5OrLess() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 1, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(7, app.items[0].quality)
    }
    @Test
    fun BackstageAtSellIn() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 0, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }
    @Test
    fun BackstageSellInPassed() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", -1, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }
}


