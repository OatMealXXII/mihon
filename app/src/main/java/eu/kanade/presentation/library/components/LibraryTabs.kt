package eu.kanade.presentation.library.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.zIndex
import eu.kanade.presentation.category.visualName
import tachiyomi.domain.category.model.Category
import tachiyomi.presentation.core.components.ComicSpeechBubbleShape
import tachiyomi.presentation.core.components.comicBorder
import tachiyomi.presentation.core.components.material.TabText

@Composable
internal fun LibraryTabs(
    categories: List<Category>,
    pagerState: PagerState,
    getItemCountForCategory: (Category) -> Int?,
    onTabItemClick: (Int) -> Unit,
) {
    val currentPageIndex = pagerState.currentPage.coerceAtMost(categories.lastIndex)
    Column(modifier = Modifier.zIndex(2f)) {
        PrimaryScrollableTabRow(
            selectedTabIndex = currentPageIndex,
            edgePadding = 0.dp,
            divider = {},
        ) {
            categories.forEachIndexed { index, category ->
                val isSelected = currentPageIndex == index
                Tab(
                    selected = isSelected,
                    onClick = { onTabItemClick(index) },
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 4.dp)
                        .zIndex(if (isSelected) 2f else 1f),
                    text = {
                        Box(
                            modifier = Modifier
                                .comicBorder(
                                    shape = ComicSpeechBubbleShape(cornerRadius = 12f),
                                    borderWidth = if (isSelected) 2.dp else 1.dp,
                                    shadowOffset = if (isSelected) 3.dp else 1.dp
                                )
                                .background(
                                    color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                                    shape = ComicSpeechBubbleShape(cornerRadius = 12f)
                                )
                                .padding(start = 12.dp, top = 6.dp, end = 12.dp, bottom = 14.dp)
                        ) {
                            TabText(
                                text = category.visualName,
                                badgeCount = getItemCountForCategory(category),
                            )
                        }
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        HorizontalDivider()
    }
}
