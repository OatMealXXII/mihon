package eu.kanade.presentation.history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import eu.kanade.presentation.manga.components.MangaCover
import eu.kanade.presentation.theme.TachiyomiPreviewTheme
import eu.kanade.presentation.util.formatChapterNumber
import eu.kanade.tachiyomi.util.lang.sanitizeHtml
import eu.kanade.tachiyomi.util.lang.toTimestampString
import tachiyomi.domain.history.model.HistoryWithRelations
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.ComicSpeechBubbleShape
import tachiyomi.presentation.core.components.ComicPanelShape
import tachiyomi.presentation.core.components.comicBorder
import tachiyomi.presentation.core.components.material.padding
import tachiyomi.presentation.core.i18n.stringResource

private val HistoryItemHeight = 96.dp

@Composable
fun HistoryItem(
    history: HistoryWithRelations,
    onClickCover: () -> Unit,
    onClickResume: () -> Unit,
    onClickDelete: () -> Unit,
    onClickFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sanitizedTitle = remember(history.title) { history.title.sanitizeHtml() }
    val readAt = remember { history.readAt?.toTimestampString() ?: "" }
    
    Box(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .comicBorder(
                shape = ComicPanelShape(topRightOffset = 8f, bottomLeftOffset = 8f),
                borderWidth = 2.dp,
                shadowOffset = 4.dp
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = ComicPanelShape(topRightOffset = 8f, bottomLeftOffset = 8f)
            )
            .clickable(onClick = onClickResume)
            .height(HistoryItemHeight)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MangaCover.Book(
                modifier = Modifier
                    .fillMaxHeight()
                    .comicBorder(shape = RoundedCornerShape(4.dp), borderWidth = 1.dp, shadowOffset = 1.dp),
                data = history.coverData,
                onClick = onClickCover,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
            ) {
                Text(
                    text = sanitizedTitle,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .comicBorder(
                            shape = ComicSpeechBubbleShape(cornerRadius = 8f),
                            borderWidth = 1.dp,
                            shadowOffset = 2.dp
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = ComicSpeechBubbleShape(cornerRadius = 8f)
                        )
                        .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 8.dp)
                ) {
                    val bubbleText = if (history.chapterNumber > -1) {
                        stringResource(
                            MR.strings.recent_manga_time,
                            formatChapterNumber(history.chapterNumber),
                            readAt,
                        )
                    } else {
                        readAt
                    }
                    Text(
                        text = bubbleText,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            if (!history.coverData.isMangaFavorite) {
                IconButton(onClick = onClickFavorite) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = stringResource(MR.strings.add_to_library),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            IconButton(onClick = onClickDelete) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(MR.strings.action_delete),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun HistoryItemPreviews(
    @PreviewParameter(HistoryWithRelationsProvider::class)
    historyWithRelations: HistoryWithRelations,
) {
    TachiyomiPreviewTheme {
        Surface {
            HistoryItem(
                history = historyWithRelations,
                onClickCover = {},
                onClickResume = {},
                onClickDelete = {},
                onClickFavorite = {},
            )
        }
    }
}
