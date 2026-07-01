package eu.kanade.presentation.browse.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import eu.kanade.tachiyomi.util.lang.sanitizeHtml
import eu.kanade.tachiyomi.util.system.LocaleHelper
import tachiyomi.domain.source.model.Source
import tachiyomi.presentation.core.components.material.padding
import tachiyomi.presentation.core.util.secondaryItemAlpha

@Composable
fun BaseSourceItem(
    source: Source,
    modifier: Modifier = Modifier,
    showLanguageInContent: Boolean = true,
    onClickItem: () -> Unit = {},
    onLongClickItem: () -> Unit = {},
    icon: @Composable RowScope.(Source) -> Unit = defaultIcon,
    action: @Composable RowScope.(Source) -> Unit = {},
    content: @Composable RowScope.(Source, String?) -> Unit = defaultContent,
) {
    val sourceLangString = LocaleHelper.getSourceDisplayName(source.lang, LocalContext.current).takeIf {
        showLanguageInContent
    }
    BaseBrowseItem(
        modifier = modifier,
        onClickItem = onClickItem,
        onLongClickItem = onLongClickItem,
        icon = { icon.invoke(this, source) },
        action = { action.invoke(this, source) },
        content = { content.invoke(this, source, sourceLangString) },
    )
}

private val defaultIcon: @Composable RowScope.(Source) -> Unit = { source ->
    SourceIcon(source = source)
}

private val defaultContent: @Composable RowScope.(Source, String?) -> Unit = { source, sourceLangString ->
    val sanitizedName = remember(source.name) { source.name.sanitizeHtml() }
    val sanitizedLang = remember(sourceLangString) { sourceLangString?.sanitizeHtml() }
    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.padding.medium)
            .weight(1f),
    ) {
        Text(
            text = sanitizedName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        )
        if (sanitizedLang != null) {
            Text(
                modifier = Modifier.secondaryItemAlpha(),
                text = sanitizedLang,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
