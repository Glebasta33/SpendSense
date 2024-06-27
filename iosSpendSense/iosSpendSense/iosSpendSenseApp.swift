import SwiftUI
import shared

@main
struct iosSpendSenseApp: App {
    
    init() {
        IosKoin.shared.initialize(userDefaults: UserDefaults.standard)
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
