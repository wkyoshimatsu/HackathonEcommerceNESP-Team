import Image from "next/image"

export const NavBar = () => {
  return (
    <header className="flex justify-between items-center mb-4">
      <div className="flex items-center gap-2">
        <Image src="/images/account-circle.png" alt="Perfil" width={32} height={32} />
        <h1 className="text-xl font-bold">Bem-vindo, Usuário!</h1>
      </div>

      <div className="flex space-x-4">
        <Image className="object-contain w-8" src="/images/notifications.png" alt="Notificação" width={32} height={32} />
        <Image className="object-contain" src="/images/menu.png" alt="Menu" width={32} height={32} />
      </div>
    </header>
  )
}