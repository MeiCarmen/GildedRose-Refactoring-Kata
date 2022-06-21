package com.gildedrose

class GildedRose(var items: Array<Item>) {
    val decrease = 1

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
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            if (item.name != "Sulfuras, Hand of Ragnaros") {
                changeQuality(item, -1)
            }
        } else {
            changeQuality(item, 1)

            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                if (item.sellIn <= 10) {
                    changeQuality(item, 1)
                }
                if (item.sellIn <= 5) {
                    changeQuality(item, 1)
                }
            }

        }
    }

    fun updateQualityPostSellIn(item: Item) {
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            changeQuality(item, -1)
        } else {
            changeQuality(item, 1)
            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                if (item.sellIn <= 10) {
                    changeQuality(item, 1)
                }

                if (item.sellIn <= 5) {
                    changeQuality(item, 1)
                }
            }
        }
        if (item.name != "Aged Brie") {
            if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                changeQuality(item, -1)
            } else {
                item.quality = 0
            }
        } else {
            changeQuality(item, 1)
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

