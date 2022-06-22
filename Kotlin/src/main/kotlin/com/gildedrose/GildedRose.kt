package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") {
                break
            }
            updateSellIn(item)
            // make separate update quality funcs for exceptions
            if (item.sellIn >= 0) {
                updateQualityPreSellIn(item)
            } else {
                updateQualityPostSellIn(item)
            }

        }
    }

    fun updateSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }

    fun updateQualityPreSellIn(item: Item) {
        when (item.name) {
            "Aged Brie" -> changeQuality(item, 1)
            "Backstage passes to a TAFKAL80ETC concert" -> backstagePassQualityPreSellIn(item)
            "Conjured Mana Cake" -> changeQuality(item, -2)
            else -> changeQuality(item, -1)
        }
    }

    fun backstagePassQualityPreSellIn(item:Item){
        if (item.sellIn <= 5) {
            changeQuality(item, 3)
        } else if (item.sellIn <= 10) {
            changeQuality(item, 2)
        } else {
            changeQuality(item, 1)
        }
    }

    fun updateQualityPostSellIn(item: Item) {
        when (item.name) {
            "Aged Brie" -> changeQuality(item, 2)
            "Backstage passes to a TAFKAL80ETC concert" -> item.quality = 0
            "Conjured Mana Cake" -> changeQuality(item, -4)
            else -> changeQuality(item, -2)
        }
    }

    fun changeQuality(item: Item, change: Int) {
        item.quality = item.quality + change
        checkQualityBounds(item)
    }

    fun checkQualityBounds(item: Item) {
        if (item.quality < 0) {
            item.quality = 0
        } else if (item.quality > 50) {
            item.quality = 50
        }
    }
}

