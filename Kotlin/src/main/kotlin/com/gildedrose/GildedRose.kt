package com.gildedrose

class GildedRose(var items: Array<Item>) {
    private val immutables = arrayOf("Sulfuras, Hand of Ragnaros")
    private val minQuality = 0
    private val maxQuality = 50


    fun updateQuality() {
        for (item in items) {
            if (immutables.contains(item.name)) {
                break
            }
            updateSellIn(item)
            updateQuality(item)
        }
    }

    private fun updateSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }

    private fun updateQuality(item: Item) {
        if (item.sellIn >= 0) {
            updateQualityPreSellIn(item)
        } else {
            updateQualityPostSellIn(item)
        }
    }

    private fun updateQualityPreSellIn(item: Item) {
        when (item.name) {
            "Aged Brie" -> changeQuality(item, 1)
            "Backstage passes to a TAFKAL80ETC concert" -> backstagePassQualityPreSellIn(item)
            "Conjured Mana Cake" -> changeQuality(item, -2)
            else -> changeQuality(item, -1)
        }
    }

    private fun backstagePassQualityPreSellIn(item: Item) {
        if (item.sellIn <= 5) {
            changeQuality(item, 3)
        } else if (item.sellIn <= 10) {
            changeQuality(item, 2)
        } else {
            changeQuality(item, 1)
        }
    }

    private fun updateQualityPostSellIn(item: Item) {
        when (item.name) {
            "Aged Brie" -> changeQuality(item, 2)
            "Backstage passes to a TAFKAL80ETC concert" -> item.quality = 0
            "Conjured Mana Cake" -> changeQuality(item, -4)
            else -> changeQuality(item, -2)
        }
    }

    private fun changeQuality(item: Item, change: Int) {
        item.quality = item.quality + change
        checkQualityBounds(item)
    }

    private fun checkQualityBounds(item: Item) {
        if (item.quality < minQuality) {
            item.quality = minQuality
        } else if (item.quality > maxQuality) {
            item.quality = maxQuality
        }
    }
}

