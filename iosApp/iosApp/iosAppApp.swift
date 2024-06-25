import SwiftUI
import shared

@main
struct iosAppApp: App {

    init(){
        IosKoinKt.shared.initialize(userDefauls: UserDefaults.standard)
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
