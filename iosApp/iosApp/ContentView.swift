import SwiftUI
import shared

struct ContentView: View {
    let fieldService = FieldService()

    @State var result = "Loading..."
    
    func load() {
        fieldService.getFields { result, error in
            if let result = result {
                self.result = result.map { field in field.name }.joined(separator: "\n")
            } else if let error = error {
                self.result = "Error: \(error)"
            }
        }
    }
    
	var body: some View {
        Text(result).onAppear() {
            load()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
