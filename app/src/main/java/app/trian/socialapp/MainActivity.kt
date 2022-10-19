package app.trian.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.corporate.screen.BaseScreen
import com.cexup.ui.corporate.theme.CorporateTheme
import com.cexup.ui.R
import com.cexup.ui.corporate.component.SidebarMenuType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CorporateTheme {
                BaseScreen(
                    currentRoute = "",
                    onSearchPatient = {  },
                    onPatientDetail = {  },
                    onCheckUp = {  },
                    onAddPatient = {  },
                    onProfile = {  },
                    onLogout = {  },
                    onNavigate = {  },
                    listMenuSidebar = listOf(
                        SidebarMenuModel(
                            name= R.string.corporate_menu_dashboard,
                            image = R.drawable.ic_home_unselected,
                            selectedImage = R.drawable.ic_home_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_patients,
                            image = R.drawable.ic_patient_unselected ,
                            selectedImage = R.drawable.ic_patient_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_cexup,
                            image = R.drawable.ic_feature_unselected ,
                            selectedImage = R.drawable.ic_feature_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_account,
                            image = R.drawable.ic_user_unselected ,
                            selectedImage = R.drawable.ic_user_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_logout,
                            image = R.drawable.ic_logout_unselected ,
                            selectedImage = R.drawable.ic_logout_selected,
                            type= SidebarMenuType.Button,
                            path = ""
                        ),
                    )
                ) {
                    Text(text = "Test")
                }
            }
        }
    }
}
